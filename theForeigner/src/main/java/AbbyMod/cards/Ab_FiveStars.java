package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import static AbbyMod.AbbyMod.makeCardPath;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;


public class Ab_FiveStars extends AbstractDynamicCard {
    public static final String ID = AbbyMod.makeID(Ab_FiveStars.class.getSimpleName());
    public static final String IMG = makeCardPath("FiveStars.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;

    private static final int COST = 5;
    private static final int ATTACK_DMG = 10;


    public Ab_FiveStars() {
        super(ID,
                IMG,
                COST,
                CardType.ATTACK,
                COLOR,
                CardRarity.UNCOMMON,
                CardTarget.ALL_ENEMY);
        this.isMultiDamage= true;
        this.baseDamage = ATTACK_DMG;
    }

    @Override
    public void upgrade() {
        if(!this.upgraded) {
            upgradeName();
            upgradeDamage(6);
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        if(EnergyPanel.totalCount == 5){
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(2));
            AbstractDungeon.actionManager.addToBottom(new ModifyDamageAction(this.uuid, 5));
        }
    }

    public AbstractCard makeCopy() {
        return new Ab_FiveStars();
    }
}

