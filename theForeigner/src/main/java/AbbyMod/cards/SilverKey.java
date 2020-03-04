package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import static AbbyMod.AbbyMod.makeCardPath;
import AbbyMod.powers.Unlocked;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SilverKey extends AbstractDynamicCard {

    public static final String ID = AbbyMod.makeID(SilverKey.class.getSimpleName());
    public static final String IMG = makeCardPath("SilverKey.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final int COST = 3;
    private static final int ATTACK_DMG = 4;
    private static final int BLOCK = 12;
    private static final int UPGRADE = 4;

    public SilverKey() {
        super(
                ID,
                IMG,
                COST,
                CardType.ATTACK,
                COLOR,
                CardRarity.UNCOMMON,
                CardTarget.ENEMY
        );
        this.baseDamage = ATTACK_DMG;
        this.baseBlock = BLOCK;
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        AbstractCard KeyType = this.makeStatEquivalentCopy();
        KeyType.cost = 3;
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Unlocked(p,1,KeyType)));
    }

    public AbstractCard makeCopy() {
        return new SilverKey();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE);
            upgradeBlock(UPGRADE);
            initializeDescription();
        }
    }
}