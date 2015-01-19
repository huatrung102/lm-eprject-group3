/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bussiness;

import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author Administrator PC
 */
public class Fine {
    private static final int payPercentPerDay = 1;
    public static double calculateFine(int daylate,double priceBook){
        double result = daylate * (payPercentPerDay * priceBook / 100);
        return result > priceBook ? priceBook : result;                
    }
    public static String calculateTotal(JTable table,int columnPrice){
        float result = 0;
        TableModel model = table.getModel();
        for (int i = model.getRowCount() - 1; i >= 0; --i) {
            result +=  Float.valueOf(model.getValueAt(i, columnPrice).toString()) ;
        }            
        return String.valueOf(result) ;
    }
}
