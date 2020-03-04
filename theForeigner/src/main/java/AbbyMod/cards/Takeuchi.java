package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.actions.TakeuchiAction;
import AbbyMod.characters.AbbyChar;
import static AbbyMod.AbbyMod.makeCardPath;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Takeuchi extends AbstractDynamicCard {

    public static final String ID = AbbyMod.makeID(Takeuchi.class.getSimpleName());
    public static final String IMG = makeCardPath("Takeuchi.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final int COST = 1;
    private static final int DAMAGE = 5;

    public Takeuchi() {
        super(
                ID,
                IMG,
                COST,
                CardType.ATTACK,
                COLOR,
                CardRarity.UNCOMMON,
                CardTarget.ENEMY
        );
        this.exhaust = true;
        this.baseDamage = DAMAGE;
    }
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        AbstractDungeon.actionManager.addToBottom(new TakeuchiAction(p));
        if (AbstractDungeon.player.drawPile.isEmpty()) {
            AbstractDungeon.actionManager.addToTop(new EmptyDeckShuffleAction());
        }
    }

    public AbstractCard makeCopy() {
        return new Takeuchi();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(4);
            initializeDescription();
        }
    }
}