package game.engine.ui.dom;

import game.engine.ui.dom.nodes.DOMItem;
import game.engine.ui.framework.StateManager;
import game.engine.ui.framework.interfaces.IProps;
import org.apache.commons.text.StringEscapeUtils;
import org.w3c.dom.Node;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class DOMInterpreter {

    public enum TemplateRegexEnum {

        SUFFIX("\\w*(\\.\\w*)*}}$"),
        STATE("^{{\\$"+SUFFIX.REGEX),
        PROP("^{{@"+SUFFIX.REGEX),
        METHOD("^{{#"+SUFFIX.REGEX),
        ITERATOR("^{{::"+SUFFIX.REGEX),
        GENERAL_TEMPLATE("^{{(#|\\$|@|::)"+SUFFIX.REGEX);

        public final String REGEX;
        TemplateRegexEnum(String regex) {
            REGEX = regex;
        }

        boolean is(String value) {
            return value.matches(REGEX);
        }
    }

    private HashMap<Class<?>, String> regexList;
    private StateManager stateManager;
    private String attributeValue;

    public DOMInterpreter(StateManager stateManager) {
        this.stateManager = stateManager;
        regexList = new HashMap<>();
        loadRegex();
    }

    private void loadRegex() {
        String nonDecimalNumberRegex = "^-?\\d+$";
        String decimalNumberRegex = "^-?((\\d*(\\.\\d+)?)|(\\.\\d+))$";
        String booleanRegex = "^true|false$";
        String charRegex = "^.{0, 1}$";
        String stringRegex = "^(.*)$";

        regexList.put(Byte.class, nonDecimalNumberRegex);
        regexList.put(Short.class, nonDecimalNumberRegex);
        regexList.put(Integer.class, nonDecimalNumberRegex);
        regexList.put(Long.class, nonDecimalNumberRegex);

        regexList.put(Float.class, decimalNumberRegex);
        regexList.put(Double.class, decimalNumberRegex);

        regexList.put(Boolean.class, booleanRegex);

        regexList.put(Character.class, charRegex);
        regexList.put(String.class, stringRegex);
    }

    @SuppressWarnings("unchecked")
    public void loadProps(DOMItem item, Node node, IProps props) {

         Class<?> classType = props.getClass();

         Arrays.stream(classType.getFields())
                 .forEach(field -> {
                     try {
                         attributeValue = getAttributeValue(field.getName(), node);
                         /*if(shouldGetValueByTemplate() && field.getType() == List.class) {

                         } else */if (shouldGetValueByTemplate()) {
                             field.set(props, getValueByTemplate(item));
                         } else if (regexList.containsKey(field.getType())) {
                             field.set(props, getValue(field.getName(), field.getType()));
                         } else if (field.getType() == List.class) {
                             List<Object> array = new ArrayList<>();
                             Class<?> genericType = (Class<?>) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
                             List<String> rawValue = (List<String>) getArrayValue(genericType);
                             if (rawValue != null) {
                                 final int[] i = {0};
                                 rawValue.forEach(value -> {
                                     array.add(reduceLiteralType(value.trim(), genericType, field.getName()+"#item-"+ i[0]));
                                     i[0]++;
                                 });
                                 field.set(props, array);
                             }
                         }
                     } catch (IllegalAccessException e) {
                         throw new RuntimeException(e);
                     }
                 });


    }

    /**
     *
     * Custom Component children system need to be made to get it working
     *
     * @param item
     * @return
     */
    private Object getValueByTemplate(DOMItem item) {
        Object value = null;
        if(TemplateRegexEnum.STATE.is(attributeValue)) {
            value = findValue(stateManager.getState(), getValuePath());
        } else if (TemplateRegexEnum.PROP.is(attributeValue)) {

        } else if (TemplateRegexEnum.METHOD.is(attributeValue)) {

        } else if (TemplateRegexEnum.ITERATOR.is(attributeValue)) {

        }
        return value;
    }

    private String[] getValuePath() {
        return null;
    }

    private Object findValue(Object target, String[] path) {
        AtomicReference<Object> value = new AtomicReference<>();
        Arrays.stream(target.getClass().getFields())
                .filter(field -> field.getName().equals(path[0]))
                .findFirst()
                .ifPresent(field -> {
                    try {
                        value.set(field.get(target));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
        if(value.get() == null) {
            throw new RuntimeException("Value not found");
        }
        return value.get();
    }

    private boolean shouldGetValueByTemplate() {
        return attributeValue != null && attributeValue.matches(TemplateRegexEnum.GENERAL_TEMPLATE.REGEX);
    }

    @SuppressWarnings("unchecked")
    private <T> List<T> getArrayValue(Class<T> classType) {
        List<T> value;
        if(attributeValue == null) {
            return null;
        }
        if(classType == String.class || classType == Character.class) {
            value = (List<T>) getStringArrayValue(attributeValue);
        } else {
            value = (List<T>) Arrays.stream(attributeValue.split(",")).collect(Collectors.toList());
        }
        return value;
    }

    private <T> T getValue(String fieldName, Class<T> classType) {
        if(attributeValue == null) {
            return null;
        }
        return reduceLiteralType(attributeValue, classType, fieldName);
    }

    @SuppressWarnings("unchecked")
    private <T> T reduceLiteralType(String attributeValue, Class<T> classType, String attributeName) {
        if(!attributeValue.matches(regexList.get(classType))) {
            throw new RuntimeException("Attribute type syntax error!\n Attribute: "+attributeName+", type: "+classType.getName());
        }
        if(classType == Byte.class) {
            return (T) Byte.valueOf(attributeValue);
        } else if (classType == Short.class) {
            return (T) Short.valueOf(attributeValue);
        } else if (classType == Integer.class) {
            return (T) Integer.valueOf(attributeValue);
        } else if (classType == Long.class) {
            return (T) Long.valueOf(attributeValue);
        } else if (classType == Float.class) {
            return (T) Float.valueOf(attributeValue);
        } else if (classType == Double.class) {
            return (T) Double.valueOf(attributeValue);
        } else if (classType == Boolean.class) {
            return (T) Boolean.valueOf(attributeValue);
        } else if (classType == Character.class) {
            String escaped = StringEscapeUtils.unescapeJava(attributeValue);
            if(escaped.length() != 1) {
                throw new RuntimeException("Attribute type syntax error!\n Attribute: "+attributeName+", type: "+classType.getName());
            }
            return (T) escaped;
        } else if (classType == String.class) {
            return (T) attributeValue;
        }

        return null;
    }

    private boolean isLiteralDecimal(Class<?> classType) {
        return (
                classType.getName().equals(Float.class.getName()) ||
                classType.getName().equals(Double.class.getName())
        );
    }

    private boolean isLiteralBoolean(Class<?> classType) {
        return classType.getName().equals(Byte.class.getName());
    }

    private boolean isLiteralCharacter(Class<?> classType) {
        return classType.getName().equals(Character.class.getName());
    }

    private boolean isLiteralString(Class<?> classType) {
        return classType.getName().equals(String.class.getName());
    }

    private String getAttributeValue(String attributeName, Node node) {
        int attributesCount = node.getAttributes().getLength();
        for(int i = 0; i < attributesCount; i++) {
            if(node.getAttributes().item(i).getNodeName().equals(attributeName)) {
                return node.getAttributes().item(i).getNodeValue().trim();
            }
        }
        return null;
    }

    /**
     *  O valor da string é retirado das aspas
     * */
    private List<String> getStringArrayValue(String value) {
        List<String> array = new ArrayList<>();
        StringBuilder word = new StringBuilder();
        char letter;
        boolean readingValue = false, needNextValue = false;

        for(int i = 0; i < value.length(); i++) {
            letter = value.charAt(i);

            if(letter == ' ' && !readingValue) continue;

            if(readingValue) {
               if(letter == '\'' && value.charAt(i-1) != '\\') {
                   readingValue = false;
                   needNextValue = false;
                   array.add(word.toString());
               } else {
                   word.append(letter);
               }
            } else {
                if(letter == '\'') {
                    readingValue = true;
                } else if (letter == ',') {
                    needNextValue = true;
                }
            }

        }
        if(readingValue || needNextValue) {
            throw new RuntimeException("String array syntax error");
        }
        return array;
    }

}
