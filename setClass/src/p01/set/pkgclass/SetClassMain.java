/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p01.set.pkgclass;

/**
 *
 * @author ggear
 */
public class SetClassMain 
{
        
    public static void main(String[] args) 
    {
        SetClass set_1 = new SetClass();
        SetClass set_2 = new SetClass();
        SetClass set_3 = new SetClass();
        
        set_1.loadItems(5, 10, 104, 1);
        set_2.loadItems(5, 15, 102, 1);
        set_3.loadItems(10, 3, 101, 10);        
        System.out.println("Set 1: " + set_1.toString());
        System.out.println("Set 2: " + set_2.toString());
        System.out.println("Set 3: " + set_3.toString());
        System.out.println("Union Sets 1/2: " + set_1.findUnion(set_2).toString());
        System.out.println("Intersecting Set 1/2: " + set_1.findIntersection(set_2).toString());
        System.out.println("Relative complement Sets 1/2: " + set_1.findRelativeComplementOfThisSetIn(set_2).toString());

/*
Expected OutPut:
Set 1: 5, 7, 11, 13, 17, 19, 23, 29, 31, 37
Set 2: 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33
Set 3: 10, 20, 30
Union Sets 1/2: 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 9, 15, 21, 25, 27, 33, 37
Intersection Sets 1/2: 5, 7, 11, 13, 17, 19, 23, 29, 31
Relative complement Sets 1/2: 9, 15, 21, 25, 27, 33
Power Set of Set 3: -; 10; 20; 10, 20; 30; 10, 30; 20, 30; 10, 20, 30
*/
        

    }

}
