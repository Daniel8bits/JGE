package game.engine.ui.dom;

import game.engine.ui.dom.nodes.DOMItem;
import game.engine.ui.framework.interfaces.IProps;
import org.apache.commons.text.StringEscapeUtils;
import org.w3c.dom.Node;

import java.util.Arrays;
import java.util.HashMap;

public class DOMInterpreter {

    private HashMap<Class<?>, String> regexList;

    public DOMInterpreter() {
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

    public void loadProps(DOMItem item, Node node, IProps props) {

         Class<?> classType = props.getClass();

         Arrays.stream(classType.getFields())
                 .forEach(field -> {
                     if(regexList.containsKey(field.getType())) {
                         try {
                             field.set(props, getValue(field.getName(), node, field.getType()));
                         } catch (IllegalAccessException e) {
                             throw new RuntimeException(e);
                         }
                     }
                 });

    }

    private <T> T getValue(String fieldName, Node node, Class<T> classType) {
        String attributeValue = null;
        int attributesCount = node.getAttributes().getLength();
        for(int i = 0; i < attributesCount; i++) {
            if(node.getAttributes().item(i).getNodeName().equals(fieldName)) {
                attributeValue = node.getAttributes().item(i).getNodeValue();
                break;
            }
        }
        if(attributeValue == null) {
            return null;
        }
        if(!attributeValue.matches(regexList.get(classType))) {
            throw new RuntimeException("Attribute type syntax error!\n Attribute: "+fieldName+", type: "+classType.getName());
        }
        return reduceLiteralType(attributeValue, classType, fieldName);
    }

    @SuppressWarnings("unchecked")
    private <T> T reduceLiteralType(String attributeValue, Class<T> classType, String attributeName) {
        if(classType.getName().equals(Byte.class.getName())) {
            return (T) Byte.valueOf(attributeValue);
        } else if (classType.getName().equals(Short.class.getName())) {
            return (T) Short.valueOf(attributeValue);
        } else if (classType.getName().equals(Integer.class.getName())) {
            return (T) Integer.valueOf(attributeValue);
        } else if (classType.getName().equals(Long.class.getName())) {
            return (T) Long.valueOf(attributeValue);
        } else if (classType.getName().equals(Float.class.getName())) {
            return (T) Float.valueOf(attributeValue);
        } else if (classType.getName().equals(Double.class.getName())) {
            return (T) Double.valueOf(attributeValue);
        } else if (classType.getName().equals(Boolean.class.getName())) {
            return (T) Boolean.valueOf(attributeValue);
        } else if (classType.getName().equals(Character.class.getName())) {
            String escaped = StringEscapeUtils.unescapeJava(attributeValue);
            if(escaped.length() != 1) {
                throw new RuntimeException("Attribute type syntax error!\n Attribute: "+attributeName+", type: "+classType.getName());
            }
            return (T) escaped;
        } else if (classType.getName().equals(String.class.getName())) {
            return (T) attributeValue;
        }

        return null;
    }
/*
    private boolean isLiteralNonDecimal(Class<?> classType) {
        return (

        );
    }
*/
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



}
