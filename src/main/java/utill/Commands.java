package utill;

public interface Commands {
    String EXIT = "0";
    String ADD_CATEGORY = "1";
    String CHANGE_CATEGORY_BY_ID = "2";
    String DELETE_CATEGORY_BY_ID = "3";
    String ADD_PRODUCT = "4";
    String EDIT_PRODUCT_BY_ID = "5";
    String DELETE_PRODUCT_BY_ID = "6";
    String PRINT_SUM_OF_PRODUCTS = "7";
    String PRINT_MIN_PRICE_OF_PRODUCT = "8";
    String PRINT_MAX_PRICE_OF_PRODUCT = "9";
    String PRINT_AVG_PRICE_OF_PRODUCT = "10";



    static void printCommands() {
        System.out.println("Please input:" + EXIT + " for end of work ");
        System.out.println("Please input:" + ADD_CATEGORY + " for add Category ");
        System.out.println("Please input:" + CHANGE_CATEGORY_BY_ID + " for change Category by profession");
        System.out.println("Please input:" + DELETE_CATEGORY_BY_ID + " for delete Category by id");
        System.out.println("Please input:" + ADD_PRODUCT + " for add Product");
        System.out.println("Please input:" + EDIT_PRODUCT_BY_ID + " for edit Product by id ");
        System.out.println("Please input:" + DELETE_PRODUCT_BY_ID + " for delete Product by id");
        System.out.println("Please input:" + PRINT_SUM_OF_PRODUCTS + "for print sum of all products ");
        System.out.println("Please input:" + PRINT_MIN_PRICE_OF_PRODUCT + "for print min price of  products ");
        System.out.println("Please input:" + PRINT_MAX_PRICE_OF_PRODUCT + "for print min price of  products ");
        System.out.println("Please input:" + PRINT_AVG_PRICE_OF_PRODUCT + "for print avg price of  products ");


    }
}
