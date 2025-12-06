package Test;
import org.junit.jupiter.api.*;
import javaa.ProductStock;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


@DisplayName("Product Stock Tests")

public class ProductStockTest {

    @BeforeAll
    static void setUpBeforeAll() {
        System.out.println("Starting ProductStock Test Suite...");
    }


    @BeforeEach
    void setUp() {
        System.out.println("Starting new test...");
        ProductStock stock;
    }

    //-----------IdTest-------------

    @DisplayName("Constructor should create object with valid Id")
    @Test
    void ConstructorWithVailedIdTest(){
      ProductStock productStock=new ProductStock("1", "WH-1-A3", 0, 0, 1);

       assertNotNull(productStock);

    }


    @DisplayName("Constructor should throw exception when Id is empty")
    @Test
    void ConstructorWithEmptyIdTest(){

      assertThrows(IllegalArgumentException.class, () -> {
          new ProductStock("", "WH-1-A3", 0, 0, 1);

      });
    }


    @DisplayName("Constructor should throw exception when Id is null")
    @Test
    void ConstructorWithNULLIdTest(){

        assertThrows(IllegalArgumentException.class, () -> {
            new ProductStock(null, "WH-1-A3", 0, 0, 1);

        });
    }

    @DisplayName("Constructor should throw exception when Id is blank")
    @Test
    void ConstructorWithBlankIdTest(){

        assertThrows(IllegalArgumentException.class, () -> {
            new ProductStock(" ", "WH-1-A3", 0, 0, 1);

        });
    }

    //--------------LocationTest--------------


    @DisplayName("Constructor should create object with valid location")
    @Test
    void ConstructorWithVailedLocationTest(){
        ProductStock productStock=new ProductStock("1", "WH-1-A3", 0, 0, 1);
        assertNotNull(productStock);

    }


    @DisplayName("Constructor should throw exception when location is empty")
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
    //----------------------------------Getters-------------------------------------
    @Test
    void testGetId(){
        ProductStock stock = new ProductStock("5","A",1,1,5);
        assertEquals("5", stock.getProductId());
    }

    @Test
    void testGetLocation(){
        ProductStock stock = new ProductStock("5","A",1,1,5);
        assertEquals("A", stock.getLocation());
    }

    @Test
    void testGetOnHand(){
        ProductStock stock = new ProductStock("1","A",2,2,10);
        assertEquals(2, stock.getOnHand());
    }

    @Test
    void testGetReserved(){
        ProductStock stock = new ProductStock("1","A",2,2,10);
        stock.reserve(1);
        assertEquals(1, stock.getReserved());
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

    @DisplayName("addStock should throw exception for zero or negative values")
    @ParameterizedTest
    @ValueSource(ints={-1,0})
    @Timeout(2)
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
    @Timeout(2)
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


            assertThrows(IllegalArgumentException.class, () -> {stock.removeDamaged(amount);});


    }

   @Test
    void removeDamagedWithValueBigerThanOnHandTest() {
        ProductStock stock = new ProductStock("1", "WH-1-A3", 5, 0, 10);
              assertThrows(IllegalStateException.class, () -> {stock.removeDamaged(6);});

    }

    @Test
    @Timeout(2)
    @Tag("sanity")
    void removeDamagedWithValidValueandAdjustReservedTest() {
        ProductStock stock = new ProductStock("1", "WH-1-A3", 5, 0, 10);

        stock.reserve(3);
        stock.removeDamaged(4);
        assertEquals(stock.getOnHand(),1);

        assertEquals(stock.getOnHand(),stock.getReserved());

    }

    @Test
    @Timeout(2)
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
    @Tag("sanity")
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
    @Timeout(2)
    void releaseReservationWithValidValue(){
        ProductStock stock = new ProductStock("1", "WH-1-A3", 5, 0, 10);
        stock.reserve(4);
        stock.releaseReservation(2);

        assertEquals(2,stock.getReserved());
    }

  //---------------------------------------Confirms shipment TEST-------------------------------


    @ParameterizedTest
    @ValueSource(ints={0,-1})
    @Timeout(2)
    @Tag("sanity")
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
    @Timeout(2)
    void shipReservedWithValidValue(){
        ProductStock stock = new ProductStock("1", "WH-1-A3", 5, 0, 10);

        stock.reserve(4);
        stock.shipReserved(2);

        assertEquals(2,stock.getReserved());
        assertEquals(3,stock.getOnHand());


    }

    //-----------------------------isReorderNeeded TEST--------------------------

    @DisplayName("isReorderNeeded should return FALSE when stock is above threshold")
    @Test
    @Tag("sanity")
   void isReorderNeededTestReturnFalse(){
        ProductStock stock = new ProductStock("1", "WH-1-A3", 5, 2, 10);
        assertFalse(stock.isReorderNeeded());

     }


    @DisplayName("isReorderNeeded should return TRUE when stock is below threshold")
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

 //-----------------------------Updates max capacity TEST-----------------------------


    @ParameterizedTest
    @ValueSource(ints = {0,-1})
    void updateMaxCapacityWithZeroOrLessThanZeroAmount(int amount){
        ProductStock stock = new ProductStock("1", "WH-1-A3", 5, 0, 10);
        assertThrows(IllegalArgumentException.class,()->{stock.updateMaxCapacity(amount);});
    }

    @Test
    void updateMaxCapacityWithValueLessThanOnHand(){
        ProductStock stock = new ProductStock("1", "WH-1-A3", 5, 0, 10);
        assertThrows(IllegalStateException.class,()->{stock.updateMaxCapacity(4);});
    }


    @Test
    void updateMaxCapacityWithValidValueTest(){
        ProductStock stock = new ProductStock("1", "WH-1-A3", 5, 0, 10);
        stock.updateMaxCapacity(6);
        assertEquals(6,stock.getMaxCapacity());
    }

    @Test
    @Timeout(2)
    void updateMaxCapacityWithValueLessThanReorderThresholdTest(){
        ProductStock stock = new ProductStock("1", "WH-1-A3", 2, 3, 10);
           stock.updateMaxCapacity(2);
        assertEquals(2,stock.getReorderThreshold());
    }

    //-------------------Feature not implemented yet Test------------------
    @Test
    @Disabled("Feature not implemented yet")
    @DisplayName("Future feature: automatic reorder calculation")
    void futureReorderFeatureTest() {
        // Code will be added later
    }


    @AfterEach
    void tearDown() {
        System.out.println("Test finished.");
    }


    @AfterAll
    static void tearDownAfterAll() {
        System.out.println("All tests finished.");
    }
}
