package Test;
import org.junit.jupiter.api.Test;
import javaa.ProductStock;
import static org.junit.jupiter.api.Assertions.*;


public class ProductStockTest {

    //-----------IdTest-------------

    @Test
    void ConstructorWithVailedIdTest(){
      ProductStock productStock=new ProductStock("1", "WH-1-A3", 0, 0, 1);

       assertNotNull(productStock);

    }

    @Test
    void ConstructorWithEmptyIdTest(){

      assertThrows(IllegalArgumentException.class, () -> {
          new ProductStock("", "WH-1-A3", 0, 0, 1);

      });
    }

    @Test
    void ConstructorWithNULLIdTest(){

        assertThrows(IllegalArgumentException.class, () -> {
            new ProductStock(null, "WH-1-A3", 0, 0, 1);

        });
    }

    @Test
    void ConstructorWithBlankIdTest(){

        assertThrows(IllegalArgumentException.class, () -> {
            new ProductStock(" ", "WH-1-A3", 0, 0, 1);

        });
    }

    //--------------LocationTest--------------

    @Test
    void ConstructorWithVailedLocationTest(){
        ProductStock productStock=new ProductStock("1", "WH-1-A3", 0, 0, 1);
        assertNotNull(productStock);

    }

    @Test
    void ConstructorWithEmptyLocationTest(){

        assertThrows(IllegalArgumentException.class, () -> {
            new ProductStock("1", "", 0, 0, 1);

        });
    }

    @Test
    void ConstructorWithNullLocationTest(){

        assertThrows(IllegalArgumentException.class, () -> {
            new ProductStock("1", null, 0, 0, 1);

        });
    }

    @Test
    void ConstructorWithBlankocationTest(){

        assertThrows(IllegalArgumentException.class, () -> {
            new ProductStock("1", "  ", 0, 0, 1);

        });
    }

 //---------------initialOnHandTest-------------------
 @Test
 void ConstructorWithVailedInitialOnHandTest(){
     ProductStock productStock=new ProductStock("1", "WH-1-A3", 0, 0, 1);

     assertNotNull(productStock);

 }
    @Test
    void ConstructorWithInvailedInitialOnHandTest(){

        assertThrows(IllegalArgumentException.class, () -> {
            new ProductStock("1", "WH-1-A3", -1, 0, 1);
        });

    }

    //-----------------------reorderThresholdTest--------------------
    @Test
    void ConstructorWithVailedReorderThresholdTest(){
        ProductStock productStock=new ProductStock("1", "WH-1-A3", 0, 0, 1);
        assertNotNull(productStock);

    }
    @Test
    void ConstructorWithInvailedReorderThresholdTest(){

        assertThrows(IllegalArgumentException.class, () -> {
            new ProductStock("1", "WH-1-A3", 0, -1, 1);
        });

    }

    //-----------------maxCapacityTest----------------------

    @Test
    void ConstructorWithVailedMaxCapacityTest(){
        ProductStock productStock=new ProductStock("1", "WH-1-A3", 0, 0, 1);
        assertNotNull(productStock);

    }

    @Test
    void ConstructorWithInvailedMaxCapacityTest(){

        assertThrows(IllegalArgumentException.class, () -> {
            new ProductStock("1", "WH-1-A3", 0, 0, 0);
        });

    }

    //-------------------------initialOnHand >  maxCapacity Test------------------------

    @Test
    void initialOnHandSmallerOrEqualMaxCapacityTest(){
        ProductStock stock = new ProductStock("1", "WH-1-A3", 5, 0, 5);
        assertNotNull(stock);
    }

    @Test
    void MaxCapacityBigerThanInitialOnHandTest(){


        assertThrows(IllegalArgumentException.class, () -> {
            new ProductStock("1", "WH-1-A3", 5, 0, 1);
        });

    }



}
