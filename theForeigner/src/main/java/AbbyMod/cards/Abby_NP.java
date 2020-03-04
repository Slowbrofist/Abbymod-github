package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import static AbbyMod.AbbyMod.makeCardPath;
import AbbyMod.powers.KeyholeToSomewhere;
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

public class Abby_NP extends AbstractDynamicCard {
    public static final String ID = AbbyMod.makeID(Abby_NP.class.getSimpleName());
    public static final String IMG = makeCardPath("Abby_NP.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final int COST = 0;
    private static final int ATTACK_DMG = 6;
    public Abby_NP(){
        super(
                ID,
                IMG,
                COST,
                AbstractCard.CardType.ATTACK,
                COLOR,
                AbstractCard.CardRarity.BASIC,
                AbstractCard.CardTarget.ENEMY
        );
        this.baseDamage = ATTACK_DMG;
        this.purgeOnUse = true;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeDamage(2);
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(
                        m,
                        new DamageInfo(p, this.damage, this.damageTypeForTurn),
                        AbstractGameAction.AttackEffect.SLASH_VERTICAL
                )
        );
        AbstractDungeon.actionManager.addToTop(
                new ApplyPowerAction(
                        p,
                        p,
                        new KeyholeToSomewhere(p, 1),
                        1
                )
        );
    }
}
