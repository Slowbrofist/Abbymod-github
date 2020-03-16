package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import basemod.abstracts.CustomSavable;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static AbbyMod.AbbyMod.makeCardPath;

public class StarRadiance extends AbstractDynamicCard implements CustomSavable<Integer>, StartupCard {

    public static final String ID = AbbyMod.makeID(StarRadiance.class.getSimpleName());
    public static final String IMG = makeCardPath("StarRadiance.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final int COST = 2;
    private static final int  BLOCK = 5;
    private static int COUNTER = 0;



    public StarRadiance() {
        super(
                ID,
                IMG,
                COST,
                CardType.SKILL,
                COLOR,
                CardRarity.UNCOMMON,
                CardTarget.SELF
        );
        this.baseBlock = BLOCK;
        this.baseMagicNumber = this.magicNumber = COUNTER;
        this.purgeOnUse = true;

    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
        if(COUNTER < 10)
            COUNTER++;
    }

    @Override
    public void applyPowers() {
        this.baseMagicNumber = this.magicNumber = COUNTER;
        super.applyPowers();
    }

    public AbstractCard makeCopy() {
        return new StarRadiance();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(3);
            initializeDescription();
        }
    }

    public static void resetCounter(){
        COUNTER = 1;
        return;
    }

    public Integer onSave(){
        return COUNTER;
    }

    @Override
    public void onLoad(Integer integer) {
        COUNTER = integer;
    }

    @Override
    public boolean atBattleStartPreDraw() {
        addToBot(new GainBlockAction(AbstractDungeon.player, COUNTER));
        return true;
    }
}