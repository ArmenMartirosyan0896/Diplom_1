import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    Bun bun;
    @Mock
    Ingredient firstIngredient;
    @Mock
    Ingredient secondIngredient;


    @Test
    public void setBunsTest() {
        burger = Mockito.spy(new Burger());
        burger.setBuns(bun);
        Mockito.verify(burger).setBuns(bun);
    }

    @Test
    public void addIngredientTest() {
        burger = Mockito.spy(new Burger());
        burger.addIngredient(firstIngredient);
        Mockito.verify(burger).addIngredient(firstIngredient);
    }

    @Test
    public void removeIngredientTest() {
        burger = Mockito.spy(new Burger());
        burger.addIngredient(firstIngredient);
        burger.removeIngredient(0);
        Mockito.verify(burger).removeIngredient(0);
    }

    @Test
    public void moveIngredientTest() {
        burger = Mockito.spy(new Burger());
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);
        burger.moveIngredient(0, 1);
        Mockito.verify(burger).moveIngredient(0, 1);
    }

    @Test
    public void getPriceTest() {
        burger = Mockito.spy(new Burger());
        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        Mockito.when(burger.getPrice()).thenReturn(100f);
        Assert.assertEquals(100f, burger.getPrice(), Float.NaN);
    }

    @Test
    public void getReceiptTest() {
        burger = Mockito.spy(new Burger());
        burger.setBuns(bun);
        burger.getReceipt();
        Mockito.verify(burger).getReceipt();
    }

    @Test
    public void checkCorrectReceiptTest() {

        Mockito.when(bun.getName()).thenReturn("black bun");
        Mockito.when(firstIngredient.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(firstIngredient.getName()).thenReturn("sour cream");
        Mockito.when(secondIngredient.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(secondIngredient.getName()).thenReturn("cutlet");
        Mockito.when(bun.getPrice()).thenReturn(100.0f);
        Mockito.when(firstIngredient.getPrice()).thenReturn(200.0f);
        Mockito.when(secondIngredient.getPrice()).thenReturn(100.0f);

        burger = Mockito.spy(new Burger());
        burger.setBuns(bun);
        burger.addIngredient(firstIngredient);
        burger.addIngredient(secondIngredient);

        String expectedReceipt = "(==== black bun ====)\n" +
                "= sauce sour cream =\n" +
                "= filling cutlet =\n" +
                "(==== black bun ====)\n" +
                String.format("%nPrice: %f%n", 500.0f);

        String actualReceipt = burger.getReceipt();
        Assert.assertEquals("Receipt format is incorrect", expectedReceipt, actualReceipt);
    }

}
