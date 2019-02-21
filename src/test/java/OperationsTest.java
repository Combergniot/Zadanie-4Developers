import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class OperationsTest extends Operations{


    @Test
    public void shouldSayThatCuboidFieldIsEqual_52(){
        Cuboid cuboid = new Cuboid(2,3,4);
        Assert.assertEquals(52, calculateCuboidField(cuboid));
    }


    @Test
    public void shouldSayThatLastElementOnListHasParameterEqual_10x9x8() throws IOException {
        List<String> parameters = collectData();
        Assert.assertEquals("10x9x8", parameters.get(parameters.size()-1));
    }

    @Test
    public void shouldSayThatThirdCuboidFromListHasParameterEqual_27x2x5() throws IOException {
        List<Cuboid> cuboidList = createCuboidList();
        Assert.assertEquals(27, cuboidList.get(2).getA());
        Assert.assertEquals(2, cuboidList.get(2).getB());
        Assert.assertEquals(5, cuboidList.get(2).getH());
    }

    @Test
    public void shouldFindTheSmallestWallSize(){
        Cuboid cuboid = new Cuboid(12,15,15);
        Assert.assertEquals(180, getSizeTheSmallestWall(cuboid));

        Cuboid cuboid2 = new Cuboid(2,3,4);
        Assert.assertEquals(6, getSizeTheSmallestWall(cuboid2));

        Cuboid cuboid3 = new Cuboid(5,5,5);
        Assert.assertEquals(25, getSizeTheSmallestWall(cuboid3));

        Cuboid cuboid4 = new Cuboid(15,12,10);
        Assert.assertEquals(120, getSizeTheSmallestWall(cuboid4));


    }

}