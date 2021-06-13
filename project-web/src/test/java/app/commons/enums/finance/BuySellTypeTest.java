package app.commons.enums.finance;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.rules.ExpectedException;

class BuySellTypeTest {

    @Rule
    private ExpectedException expectedException;

    @BeforeAll
    public void init(){
        this.expectedException = ExpectedException.none();
    }

    @Test
    public void whenBuySellTypeIsOneReturnsBUY(){
        Assert.assertEquals(BuySellType.BUY, BuySellType.convertFrom(1));
    }

    @Test
    public void whenBuySellTypeIsThreeReturnsSELL(){
        Assert.assertEquals(BuySellType.SELL, BuySellType.convertFrom(3));
    }

    @Test
    public void whenBuySellTypeIsNineReturnsOHTER(){
        Assert.assertEquals(BuySellType.OTHER, BuySellType.convertFrom(9));
    }

    @Test(expected = IllegalArgumentException.class)
    public void throwsExceptions(){
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("Unexpected Argument. buySellType = 99");

        BuySellType buySellType = BuySellType.convertFrom(99);
    }

}