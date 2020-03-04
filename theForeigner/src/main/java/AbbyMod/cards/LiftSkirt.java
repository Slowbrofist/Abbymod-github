package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;

import static AbbyMod.AbbyMod.enableSFW;
import static AbbyMod.AbbyMod.makeCardPath;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.unique.ExhumeAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

public class LiftSkirt extends CustomCard {
    public static final String ID = AbbyMod.makeID(LiftSkirt.class.getSimpleName());
    public static final String IMG = makeCardPath("LiftSkirt.png");
    public static final String SFWIMG = makeCardPath("HammerSpace.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String SFWNAME= cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = 4;
    private static final int ATTACK_DMG = 10;
    private static final int UPGRADE_PLUS_DMG = 6;
    private static final int MAGIC = 3;

    public LiftSkirt() {
        super(
                ID,
                (enableSFW ? SFWNAME : NAME),
                (enableSFW ? SFWIMG : IMG),
                COST,
                DESCRIPTION,
                CardType.ATTACK,
                COLOR,
                CardRarity.UNCOMMON,
                CardTarget.ENEMY
        );
        this.baseDamage = ATTACK_DMG;
        this.baseMagicNumber = this.magicNumber = MAGIC;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new WeakPower(m,this.magicNumber,false),this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ExhumeAction(false));

    }

    public AbstractCard makeCopy() {
        return new LiftSkirt();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBaseCost(3);
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}
