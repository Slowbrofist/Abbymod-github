package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import static AbbyMod.AbbyMod.makeCardPath;
import AbbyMod.powers.KeyholeToSomewhere;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class PreciousMemories extends AbstractDynamicCard {

    public static final String ID = AbbyMod.makeID(PreciousMemories.class.getSimpleName());
    public static final String IMG = makeCardPath("PreciousMemories.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String DESCRIPTION_UPG = cardStrings.UPGRADE_DESCRIPTION;
    private static final int COST = 3;
    private static final int MAGIC = 2;

    public PreciousMemories() {
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
        this.exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new KeyholeToSomewhere(p,this.magicNumber),this.magicNumber));
        if(!p.hasPower(KeyholeToSomewhere.POWER_ID)) {
            AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(2));
        }
    }

    public AbstractCard makeCopy() {
        return new PreciousMemories();
    }


    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            this.isInnate = true;
            this.rawDescription = DESCRIPTION_UPG;
            initializeDescription();
        }
    }
}