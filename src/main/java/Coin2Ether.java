import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Coin2Ether {
    //Normal card pack probability(NCPP)
    //1 card pack contains 8 pieces guaranteed minimum 1 card is silver or better.
    //Each card has an 8% chance of being obtained as a flashcard.
    //10 card packs guaranteed minimum 1 card is rainbow card.

    public static class Card{
        boolean isCopperCard;
        boolean isSilverCard;
        boolean isGoldCard;
        boolean isRainbowCard;

        boolean isFlashCard;
        boolean isSpecial;

        public Card(){

        }

        public Card(boolean isC, boolean isSil, boolean isG, boolean isR,
                    boolean isF, boolean isSp){
            isCopperCard = isC;
            isSilverCard = isSil;
            isGoldCard = isG;
            isRainbowCard = isR;
            isFlashCard = isF;
            isSpecial = isSp;
        }

        public String cardParser() {
            String rs = "";
            if (isCopperCard) {
                rs += "铜";
            } else if (isSilverCard) {
                rs += "银";
            } else if (isGoldCard) {
                rs += "金";
            } else if (isRainbowCard) {
                rs += "虹";
            } else if (isSpecial) {
                rs += "特别奖";
            }

            if (!isSpecial && isFlashCard) {
                rs += "闪";
            }

            return rs;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            Card c = (Card) o;
            return  this.isCopperCard == c.isCopperCard &&
                    this.isSilverCard == c.isSilverCard &&
                    this.isGoldCard == c.isGoldCard &&
                    this.isRainbowCard == c.isRainbowCard &&
                    this.isSpecial == c.isSpecial &&
                    this.isFlashCard == c.isFlashCard;
        }

        @Override
        public int hashCode() {
            return  Boolean.hashCode(isCopperCard) +
                    Boolean.hashCode(isSilverCard) +
                    Boolean.hashCode(isGoldCard) +
                    Boolean.hashCode(isRainbowCard) +
                    Boolean.hashCode(isSpecial) +
                    Boolean.hashCode(isFlashCard);
        }

    }

    //非保底卡牌
    public static class NormalCard extends Card{

        public NormalCard(boolean isC, boolean isSil, boolean isG, boolean isR, boolean isF, boolean isSp) {
            super(isC, isSil, isG, isR, isF, isSp);
        }

        public NormalCard(){
            CardMaker();
        }

        private void CardMaker(){
            Random rand = new Random();

            int copper = 6744;
            int silver = 9244;
            int gold = 9844;
            int rainbow = 9994;
            int special = 10000;

            int cardChance = rand.nextInt(10000) + 1;

            if(cardChance <= 6744){
                isCopperCard = true;
            }else if(cardChance > 6744 && cardChance <= 9244){
                isSilverCard = true;
            }else if(cardChance > 9244 && cardChance <= 9844){
                isGoldCard = true;
            }else if(cardChance > 9844 && cardChance <= 9994){
                isRainbowCard = true;
            }else if(cardChance > 9994 && cardChance <= 10000){
                isSpecial = true;
            }

            int flash = 8;
            int flashChance = rand.nextInt(100) + 1;

            if(flashChance <= flash && !isSpecial){
                isFlashCard = true;
            }

        }

    }

    //保底银卡以上
    public static class silverBetterCard extends Card{
        public silverBetterCard(boolean isC, boolean isSil, boolean isG, boolean isR, boolean isF, boolean isSp) {
            super(isC, isSil, isG, isR, isF, isSp);
        }

        public silverBetterCard(){
            CardMaker();
        }

        private void CardMaker(){
            Random rand = new Random();

            int silver = 9244;
            int gold = 9844;
            int rainbow = 9994;
            int special = 10000;

            int cardChance = rand.nextInt(10000) + 1;

            if(cardChance <= 9244){
                isSilverCard = true;
            }else if(cardChance > 9244 && cardChance <= 9844){
                isGoldCard = true;
            }else if(cardChance > 9844 && cardChance <= 9994){
                isRainbowCard = true;
            }else if(cardChance > 9994 && cardChance <= 10000){
                isSpecial = true;
            }

            int flash = 8;
            int flashChance = rand.nextInt(100) + 1;

            if(flashChance <= flash && !isSpecial){
                isFlashCard = true;
            }

        }
    }

    //必出虹卡
    public static class mustRainbow extends Card{
        public mustRainbow(boolean isC, boolean isSil, boolean isG, boolean isR, boolean isF, boolean isSp) {
            super(isC, isSil, isG, isR, isF, isSp);
        }

        public mustRainbow(){
            CardMaker();
        }

        private void CardMaker(){
            Random rand = new Random();

            isRainbowCard = true;

            int flash = 8;
            int flashChance = rand.nextInt(100) + 1;

            if(flashChance <= flash  && !isSpecial){
                isFlashCard = true;
            }
        }
    }

    public static class Draw {
        HashMap<Card, Integer> statisticCards;

        ArrayList<String> logs;

        int noRainbow;

        int drawPacks;

        public Draw(int noRainbow){
            logs = new ArrayList<>();

            drawPacks = 0;
            this.noRainbow = noRainbow;
            statisticCards = new HashMap<>();
        }

        public Draw(){
            logs = new ArrayList<>();

            drawPacks = 0;
            this.noRainbow = 0;
            statisticCards = new HashMap<>();
        }

        public void drawCard(int n){
            for(int i = 0; i < n; i++){
                drawAPack();
            }
        }

        public void drawAPack(){
            drawPacks += 1;

            boolean existRainbowCard = false;
            //必虹
            if(noRainbow == 9){
                existRainbowCard = drawAMustRainbowPack();
            }else{
                existRainbowCard = drawANormalPack();
            }

            if(existRainbowCard){
                noRainbow = 0;
            }else{
                noRainbow += 1;
            }
        }

        private boolean drawAMustRainbowPack(){
            boolean existRainbow = false;

            for(int i = 1; i <= 8; i++){
                if(i == 1){
                    String info = "";

                    Card curCard = new silverBetterCard();
                    statisticCards.put(curCard, statisticCards.getOrDefault(curCard, 0) + 1);
                    if(curCard.isRainbowCard){
                        existRainbow = true;
                    }

                    info += drawPacks + " pack : " + i +" card is " + curCard.cardParser();

                    logs.add(info);

                    continue;
                }
                if(i == 2){
                    String info = "";

                    Card curCard = new mustRainbow();
                    statisticCards.put(curCard, statisticCards.getOrDefault(curCard, 0) + 1);
                    if(curCard.isRainbowCard){
                        existRainbow = true;
                    }

                    info += drawPacks + " pack : " + i +" card is " + curCard.cardParser();

                    logs.add(info);

                    continue;
                }
                String info = "";

                Card curCard = new NormalCard();
                statisticCards.put(curCard, statisticCards.getOrDefault(curCard, 0) + 1);
                if(curCard.isRainbowCard){
                    existRainbow = true;
                }

                info += drawPacks + " pack : " + i +" card is " + curCard.cardParser();

                logs.add(info);

            }

            return existRainbow;
        }

        private boolean drawANormalPack(){
            boolean existRainbow = false;

            for(int i = 1; i <= 8; i++){
                if(i == 1){
                    String info = "";

                    Card curCard = new silverBetterCard();
                    statisticCards.put(curCard, statisticCards.getOrDefault(curCard, 0) + 1);
                    if(curCard.isRainbowCard){
                        existRainbow = true;
                    }

                    info += drawPacks + " pack : " + i +" card is " + curCard.cardParser();

                    logs.add(info);

                    continue;
                }
                String info = "";

                Card curCard = new NormalCard();
                statisticCards.put(curCard, statisticCards.getOrDefault(curCard, 0) + 1);
                if(curCard.isRainbowCard){
                    existRainbow = true;
                }

                info += drawPacks + " pack : " + i +" card is " + curCard.cardParser();

                logs.add(info);

            }

            return existRainbow;
        }

        public void cardStatResult(){
            System.out.println("You had draw " + drawPacks + " packs cards");

            for (Map.Entry<Card, Integer> entry : statisticCards.entrySet()) {
                String cardType = entry.getKey().cardParser();
                int cardNum = entry.getValue();

                System.out.println(cardType + " : " + cardNum);
            }
        }

        //记录每次抽卡信息
        public void drawLogs(){
            for(String s : logs){
                System.out.println(s);
            }
        }

        public void drawLogs(int i){
            System.out.println(logs.get(i-1));
        }
    }

    public static class toEther{
        //只抽取第一弹卡包：传说揭幕
        //铜卡分解   10eth
        //铜闪分解   30eth
        //银卡分解   20eth
        //银闪分解   50eth
        //金卡分解   200eth
        //金闪分解   450eth
        //虹卡分解   1200eth
        //虹闪分解   2500eth

        //卡池：
        //23虹卡
        //37金卡
        //37银卡
        //45铜卡

        final static int COPPER_2_ETHER = 10;
        final static int COPPER_FLASH_2_ETHER = 30;
        final static int SILVER_2_ETHER = 20;
        final static int SILVER_FLASH_2_ETHER = 50;
        final static int GOLD_2_ETHER = 200;
        final static int GOLD_FLASH_2_ETHER = 450;
        final static int RAINBOW_2_ETHER = 1200;
        final static int RAINBOW_FLASH_2_ETHER = 2500;

        final static int COPPER_TOTAL = 45;
        final static int SILVER_TOTAL = 37;
        final static int GOLD_TOTAL = 37;
        final static int RAINBOW_TOTAL = 23;

        int rainbow0Of3;
        int rainbow1Of3;
        int rainbow2Of3;
        int rainbow3Of3;

        int gold0Of3;
        int gold1Of3;
        int gold2Of3;
        int gold3Of3;

//        int silver0Of3;
//        int silver1Of3;
//        int silver2Of3;

//        int copper0Of3;
//        int copper1Of3;
//        int copper2Of3;

        int drawChance;

        Draw draw;

        //全虹无闪满三
        public toEther(int coin){
            drawChance = coin / 500;
            draw = new Draw();

            rainbow0Of3 = 0;
            rainbow1Of3 = 0;
            rainbow2Of3 = 0;
            rainbow3Of3 = 23;

            gold0Of3 = 0;
            gold1Of3 = 0;
            gold2Of3 = 0;
            gold3Of3 = 37;
        }


        public toEther(int coin, int mustRainbow){
            drawChance = coin / 500;
            draw = new Draw(10 - mustRainbow);

            rainbow0Of3 = 0;
            rainbow1Of3 = 0;
            rainbow2Of3 = 0;
            rainbow3Of3 = 23;

            gold0Of3 = 0;
            gold1Of3 = 0;
            gold2Of3 = 0;
            gold3Of3 = 37;
        }

        public toEther(int coin, int mustRainbow, int r03, int r13, int r23, int r33,
                                                  int g03, int g13, int g23, int g33){
            drawChance = coin / 500;
            draw = new Draw(10 - mustRainbow);

            rainbow0Of3 = r03;
            rainbow1Of3 = r13;
            rainbow2Of3 = r23;
            rainbow3Of3 = r33;

            gold0Of3 = g03;
            gold1Of3 = g13;
            gold2Of3 = g23;
            gold3Of3 = g33;
        }

        public int card2Ether(){
            draw.drawCard(drawChance);

            int rToE = rainbowToEther(draw.statisticCards);
            int gToE = goldToEther(draw.statisticCards);
            int sToE = silverToEther(draw.statisticCards);
            int cToE = copperToEther(draw.statisticCards);

            return rToE + gToE + sToE + cToE;
        }

        public int getRainbowCardTotal(HashMap<Card, Integer> drawResult, boolean isFlash){

            Card rainbowCard = new Card(false,false,false,true,false,false);
            Card rainbowFlashCard = new Card(false,false,false,true,true,false);

            Card pointedCard;

            if(isFlash){
                pointedCard = rainbowFlashCard;
            }else{
                pointedCard = rainbowCard;
            }

            if(drawResult.containsKey(pointedCard)){

                int totalRainbow = drawResult.get(pointedCard);
                return totalRainbow;

            }else{
                return 0;
            }

        }

        private int rainbowToEther(HashMap<Card, Integer> drawResult){
            int rainbow2Ether = 0;

            //虹卡总数
            int rainbows = getRainbowCardTotal(drawResult, false);
            //System.out.println("虹卡 ：" + rainbows);

            //虹闪总数
            int rainbowFlashs = getRainbowCardTotal(drawResult, true);
            //System.out.println("虹闪 ：" + rainbowFlashs);

            //虹卡占比
            double full3 = (double) rainbow3Of3 / RAINBOW_TOTAL;
            double full2 = (double) rainbow2Of3 / RAINBOW_TOTAL;
            double full1 = (double) rainbow1Of3 / RAINBOW_TOTAL;
            double full0 = (double) rainbow0Of3 / RAINBOW_TOTAL;

            for(int i = 0; i < rainbows; i++){
                double r = Math.random();   // [0.0, 1.0)
                if(r < full0){
                    //抽到一张新虹卡
                    rainbow0Of3 -= 1;
                    rainbow1Of3 += 1;
                    full0 = (double) rainbow0Of3 / RAINBOW_TOTAL;
                    full1 = (double) rainbow1Of3 / RAINBOW_TOTAL;
                }else if(r >= full0 && r < (full0 + full1)){
                    rainbow1Of3 -= 1;
                    rainbow2Of3 += 1;
                    full2 = (double) rainbow2Of3 / RAINBOW_TOTAL;
                    full1 = (double) rainbow1Of3 / RAINBOW_TOTAL;
                }else if(r >= (full0 + full1) && r < (full0 + full1 + full2)){
                    rainbow2Of3 -= 1;
                    rainbow3Of3 += 1;
                    full3 = (double) rainbow3Of3 / RAINBOW_TOTAL;
                    full2 = (double) rainbow2Of3 / RAINBOW_TOTAL;
                }else{
                    rainbow2Ether += 1200;
                }
            }

            for(int i = 0; i < rainbowFlashs; i++){
                double r = Math.random();   // [0.0, 1.0)
                if(r < full0){
                    //抽到一张新虹卡
                    rainbow0Of3 -= 1;
                    rainbow1Of3 += 1;
                    full0 = (double) rainbow0Of3 / RAINBOW_TOTAL;
                    full1 = (double) rainbow1Of3 / RAINBOW_TOTAL;
                }else if(r >= full0 && r < (full0 + full1)){
                    rainbow1Of3 -= 1;
                    rainbow2Of3 += 1;
                    full2 = (double) rainbow2Of3 / RAINBOW_TOTAL;
                    full1 = (double) rainbow1Of3 / RAINBOW_TOTAL;
                }else if(r >= (full0 + full1) && r < (full0 + full1 + full2)){
                    rainbow2Of3 -= 1;
                    rainbow3Of3 += 1;
                    full3 = (double) rainbow3Of3 / RAINBOW_TOTAL;
                    full2 = (double) rainbow2Of3 / RAINBOW_TOTAL;
                }else{
                    rainbow2Ether += 2500;
                }
            }

            return rainbow2Ether;
        }

        public int getGoldCardTotal(HashMap<Card, Integer> drawResult, boolean isFlash){

            Card goldCard = new Card(false,false,true,false,false,false);
            Card goldFlashCard = new Card(false,false,true,false,true,false);

            Card pointedCard;

            if(isFlash){
                pointedCard = goldFlashCard;
            }else{
                pointedCard = goldCard;
            }

            if(drawResult.containsKey(pointedCard)){

                int totalGold = drawResult.get(pointedCard);
                return totalGold;

            }else{
                return 0;
            }

        }

        private int goldToEther(HashMap<Card, Integer> drawResult){
            int gold2Ether = 0;

            int golds = getGoldCardTotal(drawResult, false);
            //System.out.println("金卡 ：" + golds);

            int goldFlashs = getGoldCardTotal(drawResult, true);
            //System.out.println("金闪 ：" + goldFlashs);

            double full3 = (double) gold3Of3 / RAINBOW_TOTAL;
            double full2 = (double) gold2Of3 / RAINBOW_TOTAL;
            double full1 = (double) gold1Of3 / RAINBOW_TOTAL;
            double full0 = (double) gold0Of3 / RAINBOW_TOTAL;

            for(int i = 0; i < golds; i++){
                double r = Math.random();
                if(r < full0){
                    gold0Of3 -= 1;
                    gold1Of3 += 1;
                    full0 = (double) gold0Of3 / RAINBOW_TOTAL;
                    full1 = (double) gold1Of3 / RAINBOW_TOTAL;
                }else if(r >= full0 && r < (full0 + full1)){
                    gold1Of3 -= 1;
                    gold2Of3 += 1;
                    full2 = (double) gold2Of3 / RAINBOW_TOTAL;
                    full1 = (double) gold1Of3 / RAINBOW_TOTAL;
                }else if(r >= (full0 + full1) && r < (full0 + full1 + full2)){
                    gold2Of3 -= 1;
                    gold3Of3 += 1;
                    full3 = (double) gold3Of3 / RAINBOW_TOTAL;
                    full2 = (double) gold2Of3 / RAINBOW_TOTAL;
                }else{
                    gold2Ether += 200;
                }
            }

            for(int i = 0; i < goldFlashs; i++){
                double r = Math.random();
                if(r < full0){
                    gold0Of3 -= 1;
                    gold1Of3 += 1;
                    full0 = (double) gold0Of3 / RAINBOW_TOTAL;
                    full1 = (double) gold1Of3 / RAINBOW_TOTAL;
                }else if(r >= full0 && r < (full0 + full1)){
                    gold1Of3 -= 1;
                    gold2Of3 += 1;
                    full2 = (double) gold2Of3 / RAINBOW_TOTAL;
                    full1 = (double) gold1Of3 / RAINBOW_TOTAL;
                }else if(r >= (full0 + full1) && r < (full0 + full1 + full2)){
                    gold2Of3 -= 1;
                    gold3Of3 += 1;
                    full3 = (double) gold3Of3 / RAINBOW_TOTAL;
                    full2 = (double) gold2Of3 / RAINBOW_TOTAL;
                }else{
                    gold2Ether += 450;
                }
            }

            return gold2Ether;
        }

        public int getSilverCardTotal(HashMap<Card, Integer> drawResult, boolean isFlash){

            Card silverCard = new Card(false,true,false,false,false,false);
            Card silverFlashCard = new Card(false,true,false,false,true,false);

            Card pointedCard;

            if(isFlash){
                pointedCard = silverFlashCard;
            }else{
                pointedCard = silverCard;
            }

            if(drawResult.containsKey(pointedCard)){

                int totaSilver = drawResult.get(pointedCard);
                return totaSilver;

            }else{
                return 0;
            }

        }

        private int silverToEther(HashMap<Card, Integer> drawResult){
            int silver2Ether = 0;

            int silvers = getSilverCardTotal(drawResult, false);
            //System.out.println("银卡 ：" + silvers);

            int silverFlashs = getSilverCardTotal(drawResult, true);
            //System.out.println("银闪 ：" + silverFlashs);

            for(int i = 0; i < silvers; i++){
                silver2Ether += 20;
            }

            for(int i = 0; i < silverFlashs; i++){
                silver2Ether += 50;
            }

            return silver2Ether;
        }

        public int getCopperCardTotal(HashMap<Card, Integer> drawResult, boolean isFlash){

            Card copperCard = new Card(true,false,false,false,false,false);
            Card copperFlashCard = new Card(true,false,false,false,true,false);

            Card pointedCard;

            if(isFlash){
                pointedCard = copperFlashCard;
            }else{
                pointedCard = copperCard;
            }

            if(drawResult.containsKey(pointedCard)){

                int totalCopper = drawResult.get(pointedCard);
                return totalCopper;

            }else{
                return 0;
            }

        }

        private int copperToEther(HashMap<Card, Integer> drawResult){
            int copper2Ether = 0;

            int coppers = getCopperCardTotal(drawResult, false);
            //System.out.println("铜卡 ：" + coppers);

            int copperFlashs = getCopperCardTotal(drawResult, true);
            //System.out.println("铜闪 ：" + copperFlashs);


            for(int i = 0; i < coppers; i++){
                copper2Ether += COPPER_2_ETHER;
            }

            for(int i = 0; i < copperFlashs; i++){
                copper2Ether += 20;
            }

            return copper2Ether;
        }

        public void afterDrawCompare(){
            System.out.println("抽之后：");
            System.out.println("没有" + rainbow0Of3 + "种虹卡");
            System.out.println("有" + rainbow1Of3 + "种虹卡只有一张");
            System.out.println("有" + rainbow2Of3 + "种虹卡只有两张");
            System.out.println("没有" + gold0Of3 + "种金卡");
            System.out.println("有" + gold1Of3 + "种金卡只有一张");
            System.out.println("有" + gold2Of3 + "种金卡只有两张");
        }

    }
}
