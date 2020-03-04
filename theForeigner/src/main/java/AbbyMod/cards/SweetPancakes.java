package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import static AbbyMod.AbbyMod.makeCardPath;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.FleetingField;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SweetPancakes extends AbstractDynamicCard {

    public static final String ID = AbbyMod.makeID(SweetPancakes.class.getSimpleName());
    public static final String IMG = makeCardPath("SweetPancakes.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final int COST = 4;
    private static final int MAGIC = 4;

    public SweetPancakes() {
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
        this.purgeOnUse = true;
        FleetingField.fleeting.set(this, true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.player.increaseMaxHp(this.magicNumber,false);
    }

    public AbstractCard makeCopy() {
        return new SweetPancakes();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(4);
            initializeDescription();
        }
    }
}