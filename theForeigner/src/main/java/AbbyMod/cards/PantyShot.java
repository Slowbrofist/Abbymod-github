package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;

import static AbbyMod.AbbyMod.enableSFW;
import static AbbyMod.AbbyMod.makeCardPath;
import AbbyMod.powers.Madness;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PantyShot extends CustomCard {
    public static final String ID = AbbyMod.makeID(PantyShot.class.getSimpleName());
    public static final String IMG = makeCardPath("PantyShot.png");
    public static final String SFWIMG = makeCardPath("WitchStrike.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String SFWNAME = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = 1;
    private static final int ATK = 6;
    private static final int MAD = 3;


    public PantyShot() {
        super(
                ID,
                (enableSFW ? SFWNAME : NAME),
                (enableSFW ? SFWIMG : IMG),
                COST,
                DESCRIPTION,
                CardType.ATTACK,
                COLOR,
                CardRarity.RARE,
                CardTarget.ENEMY
        );
        this.baseDamage = ATK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Madness(p,MAD),MAD));
    }


    public AbstractCard makeCopy() {
        return new PantyShot();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(4);
            initializeDescription();
        }
    }
}