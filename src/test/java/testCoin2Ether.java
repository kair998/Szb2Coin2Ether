
import org.testng.annotations.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class testCoin2Ether {

    @Test
    public void testSetCard(){
        Coin2Ether.Card c = new Coin2Ether.Card(true, false, false,
                false, true, false);

        String cardName = c.cardParser();
        System.out.println(cardName);
    }

    @Test
    public void testCardProduce(){
        Coin2Ether.Card c = new Coin2Ether.NormalCard();

        String cardName = c.cardParser();
        System.out.println(cardName);
    }

    @Test
    public void testAPack(){
        Coin2Ether.Draw d = new Coin2Ether.Draw();
        d.drawAPack();
        d.cardStatResult();
    }

    @Test
    public void test10Packs(){
        Coin2Ether.Draw d = new Coin2Ether.Draw();
        for(int i = 0; i < 10; i++){
            d.drawAPack();
        }
        d.cardStatResult();
    }

    @Test
    public void testLogs(){
        Coin2Ether.Draw d = new Coin2Ether.Draw();
        for(int i = 0; i < 10; i++){
            d.drawAPack();
        }
        d.cardStatResult();
        d.drawLogs(10);
    }

    @Test
    public void testManyTimesDraw(){
        Coin2Ether.Draw d = new Coin2Ether.Draw();
        d.drawCard(5000);
        d.cardStatResult();
    }

    @Test
    public void testGetCardQuantity(){
        Coin2Ether.toEther te = new Coin2Ether.toEther(500);
        Coin2Ether.Draw d = new Coin2Ether.Draw();
        d.drawCard(100);
        //添加变量isFlash
        System.out.println(te.getRainbowCardTotal(d.statisticCards, true));
        d.cardStatResult();

        /*
        15
        You had draw 100 packs cards
        铜闪 : 50
        虹闪 : 1
        银闪 : 24
        金闪 : 3
        虹 : 15
        铜 : 408
        银 : 249
        金 : 50
         */
    }

    @Test
    public void rainbowDecompose(){
        Coin2Ether.toEther te = new Coin2Ether.toEther(10000, 4, 4, 9, 4, 6,
                1, 1, 6, 29);
        System.out.println(te.card2Ether());
        te.draw.cardStatResult();
        te.afterDrawCompare();
    }

    @Test
    public void test100Times10000Coin(){
        //10000金币抽100次
        double totalEth = 0;
        for(int i = 0; i < 10000; i++){
            Coin2Ether.toEther t = new Coin2Ether.toEther(10000);
            totalEth += t.card2Ether();
        }
        System.out.println(totalEth / 10000);
    }

    @Test
    public void testMine(){
        //10000金币抽100次
        double totalEth = 0;
        for(int i = 0; i < 10000; i++){
            Coin2Ether.toEther t = new Coin2Ether.toEther(10000, 4, 4, 9, 4, 6,
                                                                1, 1, 6, 29);
            totalEth += t.card2Ether();
        }
        System.out.println(totalEth / 10000);
    }
}
