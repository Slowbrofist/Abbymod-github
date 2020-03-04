package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;

import static AbbyMod.AbbyMod.enableSFW;
import static AbbyMod.AbbyMod.makeCardPath;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.status.Slimed;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;

public class SlimyBody extends AbstractDynamicCard {

    public static final String ID = AbbyMod.makeID(SlimyBody.class.getSimpleName());
    public static final String IMG = makeCardPath("SlimyBody.png");
    public static final String SFWIMG = makeCardPath("SFWSlimyBody.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final int COST = 2;
    private static final int MAGIC = 3;


    public SlimyBody() {
        super(
                ID,
                (enableSFW ? SFWIMG : IMG),
                COST,
                CardType.SKILL,
                COLOR,
                CardRarity.RARE,
                CardTarget.SELF
        );
        this.baseMagicNumber = this.magicNumber = MAGIC;
        this.exhaust=true;
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new IntangiblePlayerPower(p, 1), 1));
        AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(new Slimed(), this.magicNumber));

    }

    public AbstractCard makeCopy() {
        return new SlimyBody();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(-1);
            initializeDescription();
        }
    }
}
