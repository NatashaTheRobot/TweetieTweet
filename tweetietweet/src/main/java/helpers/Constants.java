package helpers;

/**
 * Created by Natasha Murashev on 2/11/14.
 */
public class Constants {
    public static enum FragmentType {

        HOME(1), MENTIONS(2), USER(3);

        private int typeCode;

        private FragmentType(int tc) {
            typeCode = tc;
        }

        public int getTypeCode() {
            return typeCode;
        }
    }
}
