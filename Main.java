package com.company;

import com.company.Model.DatabaseOperation;

public class Main {

    public static void main(String[] args) {
        DatabaseOperation operation = new DatabaseOperation();
        operation.testPush("Merve", "Parlak","arigato@sbdfhb.com","12435kufhsjh5");
        System.out.println(operation.testPull("hjsdbhsd"));
        System.out.println(operation.testPull("arigato@sbdfhb.com"));
    }


}
