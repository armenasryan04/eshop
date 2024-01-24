package utill;

import manager.CategoryManager;
import manager.ProductManager;
import models.Category;
import models.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class eshopMain implements Commands {
    private static ProductManager productManager = new ProductManager();
    private static CategoryManager categoryManager = new CategoryManager();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            Commands.printCommands();
            String commands = scanner.nextLine();
            switch (commands) {
                case EXIT:
                    isRun = false;
                    break;
                case ADD_CATEGORY:
                    addCategory();
                    break;
                case CHANGE_CATEGORY_BY_ID:
                    changeCategoryById();
                    break;
                case DELETE_CATEGORY_BY_ID:
                    deleteCategoryById();
                    break;
                case ADD_PRODUCT:
                    addProduct();
                    break;
                case EDIT_PRODUCT_BY_ID:
                    editProductById();
                    break;
                case DELETE_PRODUCT_BY_ID:
                    deleteProductId();
                    break;
                case PRINT_SUM_OF_PRODUCTS:
                    System.out.println(productManager.getSumProducts());
                    break;
                case PRINT_MIN_PRICE_OF_PRODUCT:
                    System.out.println(productManager.getMinPriceOfProducts());
                    break;
                case PRINT_MAX_PRICE_OF_PRODUCT:
                    System.out.println(productManager.getMaxPriceOfProducts());
                    break;
                case PRINT_AVG_PRICE_OF_PRODUCT:
                    System.out.println(productManager.getAvgPriceOfProducts());
            }
        }


    }

    private static void deleteProductId() {
        System.out.println(productManager.getAll());
        System.out.println("please choose id");
        String id = scanner.nextLine();
        if (productManager.getById(Integer.parseInt(id)).getId() != 0) {
            Product product = productManager.getById(Integer.parseInt(id));
            System.out.println(product + "\n you will delete this product? \n for delete input 0");
            String forAccept = scanner.nextLine();
            if (Integer.parseInt(forAccept) == 0) {
                productManager.deleteById(Integer.parseInt(id));
                System.out.println("product  was deleted!");
            }
        } else {
            System.out.println("incorrect Id! \n please try again! ");

        }
    }

    private static void editProductById() {
        System.out.println(productManager.getAll());
        System.out.println("Please choose id for change");
        int productId = Integer.parseInt(scanner.nextLine());
        try {
            if (productManager.getById(productId).getId() != 0) {
                System.out.println(productManager.getById(productId));
                System.out.println("please input category id for change");
                System.out.println(categoryManager.getAll());
                int categoryId = Integer.parseInt(String.valueOf(scanner.nextLine()));
                if (categoryManager.getById(Integer.parseInt(String.valueOf(categoryId))).getId() == 0) {
                    System.out.println("incorrect category id please try again!");
                }else {
                    System.out.println("please input name,description,quantity,price");
                    String dataOfProduct = scanner.nextLine();
                    String[] dataOfProducts = dataOfProduct.split(",");
                    Product product = new Product();
                    product.setName(dataOfProducts[0]);
                    product.setDescription(dataOfProducts[1]);
                    product.setQuantity(Integer.parseInt(dataOfProducts[2]));
                    product.setPrice(Integer.parseInt(dataOfProducts[3]));
                    product.setCategory(categoryManager.getById(categoryId));
                    product.setId(productId);
                    System.out.println(product);
                    System.out.println("your product is ready! \n for save input 0");
                    int accept = Integer.parseInt(scanner.nextLine());
                    if (accept == 0) {
                        System.out.println("your product saved! \n" + productManager.updateById(productId, product));
                    }
                }

            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("you are  input falce data please try again");
        }

    }

    private static void addProduct() throws ArrayIndexOutOfBoundsException {
        System.out.println(categoryManager.getAll());
        System.out.println("please choose category id for add product");
        String categoryId = scanner.nextLine();
        try {
            if (categoryManager.getById(Integer.parseInt(categoryId)).getId() == 0) {
                System.out.println("incorrect category id please try again!");
                addProduct();
            } else {
                System.out.println("please input name,description,quantity,price");
                String dataOfProduct = scanner.nextLine();
                String[] dataOfProducts = dataOfProduct.split(",");
                Product product = new Product();
                product.setName(dataOfProducts[0]);
                product.setDescription(dataOfProducts[1]);
                product.setQuantity(Integer.parseInt(dataOfProducts[2]));
                product.setPrice(Integer.parseInt(dataOfProducts[3]));
                product.setCategory(categoryManager.getById(Integer.parseInt(categoryId)));
                System.out.println(product);
                System.out.println("your product is ready for save input 0");
                int accept = Integer.parseInt(scanner.nextLine());
                if (accept == 0) {
                    System.out.println("your product saved! \n" + productManager.addToDb(product));
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("you  are input falce data please try again");
        }

    }

    private static void deleteCategoryById() {
        System.out.println(categoryManager.getAll());
        System.out.println("please choose id");
        String id = scanner.nextLine();
        if (categoryManager.getById(Integer.parseInt(id)).getId() != 0) {
            Category category = categoryManager.getById(Integer.parseInt(id));
            System.out.println(category + "\n you will delete this category? \n for delete input 0");
            String forAccept = scanner.nextLine();
            if (Integer.parseInt(forAccept) == 0) {
                categoryManager.deleteById(Integer.parseInt(id));
                System.out.println("category was deleted!");
            }
        } else {
            System.out.println("incorrect Id! \n please try again!");
        }

    }

    private static void changeCategoryById() {
        System.out.println(categoryManager.getAll());
        System.out.println("please choose id");
        String id = scanner.nextLine();
        if (categoryManager.getById(Integer.parseInt(id)).getId() != 0) {
            Category category = categoryManager.getById(Integer.parseInt(id));
            System.out.println(category);
            System.out.println("please input new name");
            String name = scanner.nextLine();
            System.out.println(category + " was changed " + categoryManager.updateById(Integer.parseInt(id), name));
        } else {
            System.out.println("incorrect Id! \n please try again! ");
            changeCategoryById();
        }
    }

    private static void addCategory() {
        Category category = new Category();
        System.out.println("please input category name");
        String name = scanner.nextLine();
        category.setName(name);
        System.out.println("category was added \n" + categoryManager.addToDb(category));
    }
}

