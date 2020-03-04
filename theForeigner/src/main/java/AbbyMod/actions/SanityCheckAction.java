package AbbyMod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SanityCheckAction extends AbstractGameAction {
    public SanityCheckAction(AbstractPlayer p, int amt){
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.source = p;
        this.target = p;
        this.amount = amt;
    }

    public void update() {
        if (AbstractDungeon.player.drawPile.size() + AbstractDungeon.player.discardPile.size() == 0){
            this.isDone = true;
            return;
        }
        AbstractCard target = AbstractDungeon.player.drawPile.getTopCard();
        target.modifyCostForCombat(-this.amount);
        this.isDone = true;
    }
}
