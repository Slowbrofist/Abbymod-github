package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import static AbbyMod.AbbyMod.makeCardPath;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.AlwaysRetainField;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.FrailPower;

public class Ab_PuppyEyes extends AbstractDynamicCard {

    public static final String ID = AbbyMod.makeID(Ab_PuppyEyes.class.getSimpleName());
    public static final String IMG = makeCardPath("PuppyEyes.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final int COST = 3;
    private static final int BLOCK = 30;

    public Ab_PuppyEyes() {
        super(
                ID,
                IMG,
                COST,
                CardType.SKILL,
                COLOR,
                CardRarity.RARE,
                CardTarget.SELF
        );
        this.baseBlock = BLOCK;
        this.baseMagicNumber = this.magicNumber = 3;
        AlwaysRetainField.alwaysRetain.set(this, true);
        this.isInnate = true;
        this.purgeOnUse = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new FrailPower(p, 2, false), 2));
    }

    public AbstractCard makeCopy() {
        return new Ab_PuppyEyes();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(2);
            initializeDescription();
        }
    }
}