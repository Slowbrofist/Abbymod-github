package AbbyMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class GudaGudaParticleAction extends AbstractGameAction {
    public GudaGudaParticleAction(AbstractCreature target){
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.source = AbstractDungeon.player;
        this.target = target;
    }

    public void update() {
        if (AbstractDungeon.player.drawPile.size() + AbstractDungeon.player.discardPile.size() == 0){
            this.isDone = true;
            return;
        }
        AbstractCard target = AbstractDungeon.player.drawPile.getTopCard();
        AbstractDungeon.actionManager.addToTop(new MakeTempCardInHandAction(target.makeStatEquivalentCopy()));
        this.isDone = true;
        return;
    }
}
