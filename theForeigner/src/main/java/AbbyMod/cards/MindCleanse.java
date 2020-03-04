package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import static AbbyMod.AbbyMod.makeCardPath;

import AbbyMod.powers.KeyholeToSomewhere;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class MindCleanse extends AbstractDynamicCard {

    public static final String ID = AbbyMod.makeID(MindCleanse.class.getSimpleName());
    public static final String IMG = makeCardPath("MindCleanse.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final int COST = -1;
    private static final int BLOCK_AMT = 12;
    private static final int UPGRADE_PLUS_BLOCK = 3;

    public MindCleanse() {
        super(
                ID,
                IMG,
                COST,
                CardType.SKILL,
                COLOR,
                CardRarity.RARE,
                CardTarget.SELF
        );
        this.baseBlock = BLOCK_AMT;
    }

    public void upgrade() {
        upgradeName();
        upgradeBlock(UPGRADE_PLUS_BLOCK);
        initializeDescription();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        int effect = EnergyPanel.totalCount;
        if (this.energyOnUse != -1) {
            effect = this.energyOnUse;
        }
        if (p.hasRelic("Chemical X")) {
            effect += 2;
            p.getRelic("Chemical X").flash();
        }
        for(int i=0;i<effect;i++){
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
        }
        if(!this.freeToPlayOnce) {
            p.energy.use(EnergyPanel.totalCount);
        }
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(p, p, KeyholeToSomewhere.POWER_ID));

    }
    public AbstractCard makeCopy() {
        return new MindCleanse();
    }
}

