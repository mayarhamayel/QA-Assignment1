package Test;
import org.junit.jupiter.api.Test;
import javaa.ProductStock;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    // -------------------------------------------get Available stock Test----------------------------

    @Test
    void getAvailableTest(){
       ProductStock stock= new ProductStock("1", "WH-1-A3", 10, 0, 20);
      stock.reserve(5);
      assertEquals(5,stock.getAvailable());

    }
  //-------------------------------Change physical location of the stock Test---------------------------------

    @Test
    void changeLocationWithValidValue() {
        ProductStock stock = new ProductStock("1", "WH-1-A3", 5, 0, 10);

        stock.changeLocation("WH-2-B1");

        assertEquals("WH-2-B1", stock.getLocation());
    }

    @Test
    void changeLocationWithEmptyValue() {
        ProductStock stock = new ProductStock("1", "WH-1-A3", 5, 0, 10);

        assertThrows(IllegalArgumentException.class, () -> {
            stock.changeLocation("");
        });
    }

    @Test
    void changeLocationWithBlankValue() {
        ProductStock stock = new ProductStock("1", "WH-1-A3", 5, 0, 10);

        assertThrows(IllegalArgumentException.class, () -> {
            stock.changeLocation(" ");
        });
    }

    //---------------------------------------Adds stock to on-hand quantity Test------------------------------


    @ParameterizedTest
    @ValueSource(ints={-1,0})
    void addStockWithValuelessThanZeroTest(int amount) {
        ProductStock stock = new ProductStock("1", "WH-1-A3", 5, 0, 10);

        if(amount <= 0) {
            assertThrows(IllegalArgumentException.class, () -> {stock.addStock(amount);});
        }

    }

    @ParameterizedTest
    @ValueSource(ints={16,22})
    void addStockWithInvalidValueTest(int amount) {
        ProductStock stock = new ProductStock("1", "WH-1-A3", 5, 0, 10);

        assertThrows(IllegalStateException.class, () -> {stock.addStock(amount);});


    }

    @ParameterizedTest
    @ValueSource(ints={1,5})
    void addStockWithValidValueTest(int amount) {
        ProductStock stock = new ProductStock("1", "WH-1-A3", 5, 0, 10);
       stock.addStock(amount);
        assertEquals(5+amount,stock.getOnHand());


    }

    //-----------------------Removes stock from on-hand as damaged/expired Test-------------------------


    @ParameterizedTest
    @ValueSource(ints={-1,0})
    void removeDamagedWithValuelessThanZeroTest(int amount) {
        ProductStock stock = new ProductStock("1", "WH-1-A3", 5, 0, 10);

        if(amount <= 0) {
            assertThrows(IllegalArgumentException.class, () -> {stock.removeDamaged(amount);});
        }

    }

   @Test
    void removeDamagedWithValueBigerThanOnHandTest() {
        ProductStock stock = new ProductStock("1", "WH-1-A3", 5, 0, 10);
              assertThrows(IllegalStateException.class, () -> {stock.removeDamaged(6);});

    }

    @Test
    void removeDamagedWithValidValueandAdjustReservedTest() {
        ProductStock stock = new ProductStock("1", "WH-1-A3", 5, 0, 10);

        stock.reserve(3);
        stock.removeDamaged(4);
        assertEquals(stock.getOnHand(),1);

        assertEquals(stock.getOnHand(),stock.getReserved());

    }

    @Test
    void removeDamagedWithValidValueandNoReservedCgangeTest() {
        ProductStock stock = new ProductStock("1", "WH-1-A3", 5, 0, 10);

        stock.reserve(2);
        stock.removeDamaged(2);

        assertEquals(stock.getOnHand(),3);

        assertNotEquals(stock.getOnHand(),stock.getReserved());

    }

  //------------------------------Reserves stock for a customer order TEST-----------------------
    @ParameterizedTest
    @ValueSource(ints={0,-1})
    void reserveWithZeroOrLessThanZeroAmount(int amount) {

        ProductStock stock = new ProductStock("1", "WH-1-A3", 5, 0, 10);
        assertThrows(IllegalArgumentException.class,()->{stock.reserve(amount);});


    }
    @Test
    void reserveWithValueBigerThanOnHand(){
        ProductStock stock = new ProductStock("1", "WH-1-A3", 5, 0, 10);
        assertThrows(IllegalStateException.class,()->{stock.reserve(6);});
    }

    @Test
    void reserveWithValidValue(){
        ProductStock stock = new ProductStock("1", "WH-1-A3", 5, 0, 10);
        stock.reserve(5);

        assertEquals(5,stock.getReserved());
    }

    //---------------------------Releases (un-reserves) previously reserved stock TEST--------------------------

    @ParameterizedTest
    @ValueSource(ints={0,-1})
    void releaseReservationWithZeroOrLessThanZeroAmount(int amount) {

        ProductStock stock = new ProductStock("1", "WH-1-A3", 5, 0, 10);
        assertThrows(IllegalArgumentException.class,()->{stock.releaseReservation(amount);});
    }

    @Test
    void releaseReservationWithValueBigerThanReserved(){
        ProductStock stock = new ProductStock("1", "WH-1-A3", 5, 0, 10);
        stock.reserve(4);
        assertThrows(IllegalStateException.class,()->{stock.releaseReservation(5);});
    }


    @Test
    void releaseReservationWithValidValue(){
        ProductStock stock = new ProductStock("1", "WH-1-A3", 5, 0, 10);
        stock.reserve(4);
        stock.releaseReservation(2);

        assertEquals(2,stock.getReserved());
    }

  //---------------------------------------Confirms shipment TEST-------------------------------


    @ParameterizedTest
    @ValueSource(ints={0,-1})
    void shipReservedWithZeroOrLessThanZeroAmount(int amount){

        ProductStock stock = new ProductStock("1", "WH-1-A3", 5, 0, 10);
        assertThrows(IllegalArgumentException.class,()->{stock.shipReserved(amount);});

    }

    @Test
    void shipReservedWithValueBigerThanReserved(){
        ProductStock stock = new ProductStock("1", "WH-1-A3", 5, 0, 10);
        stock.reserve(4);
        assertThrows(IllegalStateException.class,()->{stock.shipReserved(5);});
    }

    @Test
    void shipReservedWithValueBigerThanOnHand(){
        ProductStock stock = new ProductStock("1", "WH-1-A3", 5, 0, 10);

        assertThrows(IllegalStateException.class,()->{stock.shipReserved(6);});
    }


    @Test
    void shipReservedWithValidValue(){
        ProductStock stock = new ProductStock("1", "WH-1-A3", 5, 0, 10);

        stock.reserve(4);
        stock.shipReserved(2);

        assertEquals(2,stock.getReserved());
        assertEquals(3,stock.getOnHand());


    }

    //-----------------------------isReorderNeeded TEST--------------------------


   @Test
   void isReorderNeededTestReturnFalse(){
        ProductStock stock = new ProductStock("1", "WH-1-A3", 5, 2, 10);
        assertFalse(stock.isReorderNeeded());

     }

    @Test
    void isReorderNeededTestReturnTrue(){
        ProductStock stock = new ProductStock("1", "WH-1-A3", 1, 2, 10);
        assertTrue(stock.isReorderNeeded());

    }


   //-------------------------Updates the reorder threshold TEST------------------------
    @ParameterizedTest
    @ValueSource(ints={20,-1})
    void updateReorderThresholdWithValueLessThanZeroTest(int amount){

        ProductStock stock = new ProductStock("1", "WH-1-A3", 5, 0, 10);
        assertThrows(IllegalArgumentException.class,()->{stock.updateReorderThreshold(amount);});

    }

    @Test
    void updateReorderThresholdWithValidValueTest(){

        ProductStock stock = new ProductStock("1", "WH-1-A3", 5, 0, 10);
        stock.updateReorderThreshold(5);
        assertEquals(5,stock.getReorderThreshold());


    }




}
