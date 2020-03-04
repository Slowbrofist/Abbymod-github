package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import static AbbyMod.AbbyMod.makeCardPath;
import AbbyMod.powers.OldOneGospel;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.BarricadePower;


public class VoidPriest extends AbstractDynamicCard {

    public static final String ID = AbbyMod.makeID(VoidPriest.class.getSimpleName());
    public static final String IMG = makeCardPath("VoidPriest.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = 2;


    public VoidPriest() {
        super(
                ID,
                IMG,
                COST,
                CardType.POWER,
                COLOR,
                CardRarity.RARE,
                CardTarget.SELF
        );
        this.baseBlock = 0;
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (!p.hasPower("Corruption")) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BarricadePower(p)));
        }
        if (!p.hasPower("OldOneGospel")) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new OldOneGospel(p)));
        }
        if(this.upgraded){
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        }
    }


    public AbstractCard makeCopy() {
        return new VoidPriest();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(10);
            this.rawDescription = DESCRIPTION_UPG;
            initializeDescription();
        }
    }
}
