package game.engine.ui.test;

import static game.engine.ui.test.Test.EnumDecorator.decorate;
import static game.engine.ui.test.Test.TypeEnum.typeEnumOf;

public class Test {

    enum TypeEnum {
        TYPE_1("1"), TYPE_2("2"), TYPE_3("3");

        /** same as below but with nicer return type */
        public static final EnumDecorator valueOf = decorate(TypeEnum.class);
        /** Same as above but without warning */
        public static final EnumDecorator<?> valueOf_noWarning = decorate(TypeEnum.class);
        public static final EnumDecorator<TypeEnum> valueOf_opt2 = new EnumDecorator<>(TypeEnum.class);

        public static final EnumDecorator<?> typeEnumOf = decorate(TypeEnum.class);

        private final String type;

        TypeEnum(final String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }

        @Override
        public String toString() {
            return type;
        }
    }

    /**
     *  FIXME:
     *  Removing the second T in <T extends Enum<T>>
     *  and letting only <T extends Enum>
     *  results ina nicer return type for method decorate
     *  but also generates a warning in the class declaration
     **/
    static class EnumDecorator<T extends Enum<T>> {
        private final Class<T> genericEnum;

        EnumDecorator(Class<T> enumType) {
            genericEnum = enumType;
        }

        public static EnumDecorator<?> decorate(Class<?> enumType) {
            return new EnumDecorator(enumType);
        }

        public T label(String label) {
            for (var value : genericEnum.getEnumConstants())
                if (value.toString().equals(label))
                    return value;
            return null;
        }
    }

    public static void main(String[] args) {
        var type = "1";
        var typeEnum = TypeEnum.valueOf.label(type);
        var typeEnum_noWarning = TypeEnum.valueOf_noWarning.label(type);
        var type_2 = "2";
        var typeEnum_2 = TypeEnum.valueOf_opt2.label(type_2);
        System.out.println(typeEnum.getClass());
        System.out.println(typeEnum.toString());
        System.out.println(TypeEnum.TYPE_1.getClass());
        System.out.println("------------ TYPE 2 ---------------");
        System.out.println(typeEnum_2.getClass());
        System.out.println(typeEnum_2.toString());
        System.out.println(TypeEnum.TYPE_2.getClass());

        /** Bonus!! */
        var type_3 = "3";
        var typeEnum_perfectReturnType = (TypeEnum) typeEnumOf.label(type_3);
        System.out.println("------------ Bonus!! ---------------");
        System.out.println(typeEnum_perfectReturnType.getClass());
        System.out.println(typeEnum_perfectReturnType.toString());
        System.out.println(TypeEnum.TYPE_3.getClass());
    }
}
