package AbbyMod.cards;

import AbbyMod.AbbyMod;
import AbbyMod.characters.AbbyChar;
import static AbbyMod.AbbyMod.makeCardPath;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;




public class Dabigail extends AbstractDynamicCard {

  public static final String ID = AbbyMod.makeID(Dabigail.class.getSimpleName());
  public static final String IMG = makeCardPath("Dabigail.png");
  private static final CardRarity RARITY = CardRarity.COMMON;
  private static final CardTarget TARGET = CardTarget.SELF;
  private static final CardType TYPE = CardType.SKILL;
  public static final CardColor COLOR = AbbyChar.Enums.FGOAb;
  private static final int COST = 4;
  private static final int ENE = 4;
  private static final int UPG_ENE = 2;

  public Dabigail() {
    super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    this.exhaust = true;
    this.magicNumber = this.baseMagicNumber = ENE;
  }
	public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.magicNumber));
	}

  public AbstractCard makeCopy() {
    return new Dabigail();
  }

  public void upgrade() {
    if (!this.upgraded) {
      upgradeName();
      upgradeMagicNumber(UPG_ENE);
      initializeDescription();
    }
  }
}