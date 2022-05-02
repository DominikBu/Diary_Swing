public enum StudentCondition
{
    ODRABIAJACY {
        @Override public String toString() {
            return "odrabiający";
        }
    },
    CHORY {
        @Override public String toString() {
            return "chory";
        }
    },
    NIEOBECNY{
        @Override public String toString() {
            return "nieobecny";
        }
    },
}
