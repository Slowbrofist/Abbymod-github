package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import static AbbyMod.AbbyMod.makeCardPath;
import basemod.helpers.BaseModCardTags;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class Ab_Defend extends AbstractDynamicCard {
    public static final String ID = AbbyMod.makeID(Ab_Defend.class.getSimpleName());
    public static final String IMG = makeCardPath("Ab_Defend.png");
    public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
    private static final int COST = -1;
    private static final int BLOCK_AMT = 5;
    private static final int UPGRADE_PLUS_BLOCK = 2;

    public Ab_Defend() {
        super(
                ID,
                IMG,
                COST,
                CardType.SKILL,
                COLOR,
                CardRarity.BASIC,
                CardTarget.SELF
        );
        this.tags.add(CardTags.STARTER_DEFEND);
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
        if (effect > 3){
            effect = 3;
        }
        if (this.energyOnUse >= 2 && p.hasRelic("Chemical X") && !this.freeToPlayOnce){
            p.energy.use(1);
        }else if (!this.freeToPlayOnce){
            p.energy.use(effect);
        }
        effect *= this.block;
        if(effect > 0){
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, effect));
        }
    }
    public AbstractCard makeCopy() {
        return new Ab_Defend();
    }
    @Override
    public boolean isDefend() {
        return true;
    }
}
