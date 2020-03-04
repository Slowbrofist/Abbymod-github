package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import static AbbyMod.AbbyMod.makeCardPath;
import AbbyMod.powers.Madness;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.vfx.combat.MindblastEffect;

import java.util.Iterator;

public class ForeheadBeam extends AbstractDynamicCard {

    public static final String ID = AbbyMod.makeID(ForeheadBeam.class.getSimpleName());
    public static final String IMG = makeCardPath("ForeheadBeam.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final int COST = 3;
    private static final int ATTACK_DMG = 8;
    private static final int MAGIC = 2;
    public ForeheadBeam() {
        super(ID,
                IMG,
                COST,
                CardType.ATTACK,
                COLOR,
                CardRarity.BASIC,
                CardTarget.ALL_ENEMY);
        this.baseMagicNumber = this.magicNumber = MAGIC;
        this.baseDamage = ATTACK_DMG;
        this.isMultiDamage= true;
    }

    @Override
    public void upgrade() {
        if(!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            upgradeDamage(2);
            initializeDescription();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardCrawlGame.sound.play("ATTACK_MAGIC_BEAM");
        AbstractDungeon.actionManager.addToBottom(
                new VFXAction(
                        new MindblastEffect(p.dialogX, p.dialogY, false)
                )
        );
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        Iterator var3 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        AbstractMonster mo;
        while(var3.hasNext()) {
            mo = (AbstractMonster)var3.next();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new VulnerablePower(mo, this.magicNumber, false), this.magicNumber));
        }
        AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(p, p, new Madness(p, 3), 3));
    }

    public AbstractCard makeCopy() {
        return new ForeheadBeam();
    }
}
