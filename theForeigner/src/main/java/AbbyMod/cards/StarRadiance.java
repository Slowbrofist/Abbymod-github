package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import AbbyMod.powers.Madness;
import basemod.abstracts.CustomSavable;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static AbbyMod.AbbyMod.makeCardPath;

public class StarRadiance extends AbstractDynamicCard implements CustomSavable<Integer> {

    public static final String ID = AbbyMod.makeID(StarRadiance.class.getSimpleName());
    public static final String IMG = makeCardPath("StarRadiance.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final int COST = 3;
    private static final int  MAGIC = 5;
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
    this.baseMagicNumber = this.magicNumber = MAGIC;
    this.defaultBaseSecondMagicNumber = this.defaultSecondMagicNumber = COUNTER;
    this.purgeOnUse = true;

    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        COUNTER++;
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p,p,
                new Madness(p,this.magicNumber * (COUNTER)),this.magicNumber * (COUNTER)));
        }

    @Override
    public void applyPowers() {
        this.defaultBaseSecondMagicNumber = this.defaultSecondMagicNumber = COUNTER;
        super.applyPowers();
    }

    public AbstractCard makeCopy() {
        return new StarRadiance();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(3);
            initializeDescription();
        }
    }

    public static void resetCounter(){
        COUNTER = 0;
        return;
    }

    public Integer onSave(){
        return COUNTER;
    }

    @Override
    public void onLoad(Integer integer) {
        COUNTER = integer;
    }
}