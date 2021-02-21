/**
 * Hier wird das Testscenario in erster Linie konfiguriert
 * @author Vitus Henkel
 * @version 1.2
 */

// IMPORTS

import Funktionen.Szenarien;
import Hilfsmodule.EANCheck;
import User.UserManager;
import Voratsbestand.Produktverwaltung;
import Warenkorb.Warenkorbmanager;

/**
 * Class to start the program.
 * @author Vitus Henkel
 * @version 27.07.2020
 */
public class startup2 {

    /* Scenarios can be switched on or off here. */

    /* Login of the root account with all rights. */
    private static final boolean LOGIN_SUCCESS = true;

    /* Prints the absolute number of users. */
    private static final boolean TOTAL_USERS_1 = true;
    /* Prints the absolute number of users. */
    private static final boolean TOTAL_USERS_2 = true;
    /* Prints the absolute number of users. */
    private static final boolean TOTAL_USERS_3 = true;

    /* A standard user is created. */
    private static final boolean ADD_USER = true;
    /* A standard user is deleted. */
    private static final boolean DELETE_USER = true;

    /* The first standard product is created. */
    private static final boolean ADD_PRODUCT_1 = true;
    /* The first standard product is searched. */
    private static final boolean SEARCH_PRODUCT_1 = true;

    /* The second standard product is created. */
    private static final boolean ADD_PRODUCT_2 = true;
    /* The second standard product is searched. */
    private static final boolean SEARCH_PRODUCT_2 = true;
    /* The second standard product is removed. */
    private static final boolean REMOVE_PRODUCT_2 = true;

    /* A product that is not available is searched for. */
    private static final boolean SEARCH_PRODUCT_NEGATIVE = true;

    /* A list of all products is printed. */
    private static final boolean PRINT_ALL_PRODUCT_1 = true;

    /* The first standard product is handed over to the shopping cart. */
    private static final boolean BASKET_ADD_PRODUCT_1 = true;
    /* The second standard product is handed over to the shopping cart. */
    private static final boolean BASKET_ADD_PRODUCT_2 = true;

    /* The first standard product is searched in the shopping cart. */
    private static final boolean BASKET_SEARCH_PRODUCT_1 = true;
    /* The second standard product is searched in the shopping cart. */
    private static final boolean BASKET_SEARCH_PRODUCT_2 = true;

    /* The total amount of the shopping cart is issued. */
    private static final boolean BASKET_PRINT_TOTAL_AMOUNT_1 = true;
    /* The total amount of the shopping cart is issued. */
    private static final boolean BASKET_PRINT_TOTAL_AMOUNT_2 = true;

    /* The first standard product is removed from the shopping cart. */
    private static final boolean BASKET_REMOVE_PRODUCT_1 = true;


    public static void main(String[] args) {

        EANCheck.setPZCheckEnabled(false);

        UserManager userManager;                                                         //def UserManager
        userManager = UserManager.getUserManager();

        Produktverwaltung productManager = Produktverwaltung.getProduktverwaltung();     //def ProductManager
        productManager.loadProductFromDB();

        Warenkorbmanager basketManager = Warenkorbmanager.getWarenkorbmanager();         //def BasketManager


        //User

        if(LOGIN_SUCCESS)
             Szenarien.scenario_logIn_root(userManager);

        if(TOTAL_USERS_1)
            Szenarien.scenario_search_total_Amount_User_1(userManager);

        if(ADD_USER)
            Szenarien.scenario_addUser(userManager);

        if(TOTAL_USERS_2)
            Szenarien.scenario_search_total_Amount_User_2(userManager);

        if(DELETE_USER)
            Szenarien.scenario_removeUser(userManager);

        if(TOTAL_USERS_3)
            Szenarien.scenario_search_total_Amount_User_3(userManager);

        //Voratsbestand

        if(ADD_PRODUCT_1)
            Szenarien.scenario_addProduct_1(productManager);
        if(SEARCH_PRODUCT_1)
            Szenarien.scenario_searchProduct_1(productManager);

        if(ADD_PRODUCT_2)
            Szenarien.scenario_addProduct_2(productManager);
        if(SEARCH_PRODUCT_2)
            Szenarien.scenario_searchProduct_2(productManager);
        if(REMOVE_PRODUCT_2)
            Szenarien.scenario_removeProduct_2(productManager);

        if(SEARCH_PRODUCT_NEGATIVE)
            Szenarien.scenario_searchProduct_negative(productManager);

        if(PRINT_ALL_PRODUCT_1)
            Szenarien.scenario_printAllProduct_1(productManager);

        //Warenkorb

        if(BASKET_ADD_PRODUCT_1)
            Szenarien.scenario_Basket_addProduct_1(basketManager);

        if(BASKET_ADD_PRODUCT_2)
            Szenarien.scenario_Basket_addProduct_2(basketManager);

        if(BASKET_SEARCH_PRODUCT_1)
            Szenarien.scenario_Basket_searchProduct_1(basketManager);

        if(BASKET_SEARCH_PRODUCT_2)
            Szenarien.scenario_Basket_searchProduct_2(basketManager);

        if(BASKET_PRINT_TOTAL_AMOUNT_1)
            Szenarien.scenario_Basket_totalAmount_1(basketManager);

        if(BASKET_REMOVE_PRODUCT_1)
            Szenarien.scenario_Basket_removeProduct_1(basketManager);

        if(BASKET_PRINT_TOTAL_AMOUNT_2)
            Szenarien.scenario_Basket_totalAmount_2(basketManager);
    }

}
