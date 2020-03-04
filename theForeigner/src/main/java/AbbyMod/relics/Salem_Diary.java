package AbbyMod.relics;

import AbbyMod.AbbyMod;
import AbbyMod.util.TextureLoader;
import static AbbyMod.AbbyMod.makeRelicOutlinePath;
import static AbbyMod.AbbyMod.makeRelicPath;

import AbbyMod.cards.Ab_Defend;
import AbbyMod.powers.Madness;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class Salem_Diary extends CustomRelic {
    public static final String ID = AbbyMod.makeID("Salem_Diary");
    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Salem_Diary_s.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Salem_Diary_s.png"));

    public Salem_Diary() {
        super(
                ID,
                IMG,
                OUTLINE,
                RelicTier.STARTER,
                LandingSound.MAGICAL
        );
    }
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    public AbstractRelic makeCopy() {
        return new Salem_Diary();
    }
    public void onUseCard(AbstractCard card, UseCardAction action) {
        AbstractPlayer p = AbstractDungeon.player;
        int MadStack = card.costForTurn;
        flash();
        if (card.cost == -1) {
            MadStack = card.energyOnUse;
            if (card.cardID == Ab_Defend.ID && MadStack > 3){
                MadStack = 3;
            }
        }
        if (MadStack < 0) {
            MadStack = 0;
        }
        if(MadStack != 0){
        AbstractDungeon.actionManager.addToTop(
                new ApplyPowerAction(p, p, new Madness(p, MadStack), MadStack)
        );
        }
    }
}
