package AbbyMod;

import AbbyMod.relics.*;
import basemod.BaseMod;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import AbbyMod.characters.AbbyChar;
import AbbyMod.cards.*;
import AbbyMod.util.IDCheckDontTouchPls;
import AbbyMod.util.TextureLoader;
import AbbyMod.variables.DefaultCustomVariable;
import AbbyMod.variables.DefaultSecondMagicNumber;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;


@SpireInitializer
public class AbbyMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber,
        PostInitializeSubscriber,
PostDeathSubscriber{
    public static final Logger logger = LogManager.getLogger(AbbyMod.class.getName());
    private static String modID;
    private static final String MODNAME = "Abigail Mod";
    private static final String AUTHOR = "Slowbrofist";
    private static final String DESCRIPTION = "Adds the foreigner little girl, Abigail Williams from FGO.";
    public static final Color SQRGREEN = CardHelper.getColor(74.0f, 228.0f, 109.0f);
    private static final String ATTACK_FGOAb = "AbbyModResources/images/512/bg_attack_Ab_s.png";
    private static final String SKILL_FGOAb = "AbbyModResources/images/512/bg_skill_Ab_s.png";
    private static final String POWER_FGOAb = "AbbyModResources/images/512/bg_power_Ab_s.png";
    private static final String ENERGY_ORB_FGOAb = "AbbyModResources/images/512/cardOrbAb.png";
    private static final String CARD_ENERGY_ORB = "AbbyModResources/images/512/BlackStar.png";
    
    private static final String ATTACK_FGOAb_PORTRAIT = "AbbyModResources/images/1024/bg_attack_Ab.png";
    private static final String SKILL_FGOAb_PORTRAIT = "AbbyModResources/images/1024/bg_skill_Ab.png";
    private static final String POWER_FGOAb_PORTRAIT = "AbbyModResources/images/1024/bg_power_Ab.png";
    private static final String ENERGY_ORB_FGOAb_PORTRAIT = "AbbyModResources/images/1024/cardOrb_Ab.png";
    
    private static final String Abby_BUTTON = "AbbyModResources/images/charSelect/Ab_Button.png";
    private static final String Abby_PORTRAIT = "AbbyModResources/images/charSelect/Ab_Portrait.jpg";
    public static final String Abby_SHOULDER_1 = "AbbyModResources/images/char/Abigail/shoulder1.png";
    public static final String Abby_SHOULDER_2 = "AbbyModResources/images/char/Abigail/shoulder2.png";
    public static final String Abby_CORPSE = "AbbyModResources/images/char/Abigail/fallen.png";
    public static final String BADGE_IMAGE = "AbbyModResources/images/ui/Badge.png";
    public static Properties AbbyDefaultSettings = new Properties();
    public static final String ENABLE_PLACEHOLDER_SETTINGS = "enableSFW";
    public static boolean enableSFW = true; // The boolean we'll be setting on/off (true/false)
    
    public static String makeCardPath(String resourcePath) {
        return getModID() + "Resources/images/cards/" + resourcePath;
    }
    
    public static String makeRelicPath(String resourcePath) {
        return getModID() + "Resources/images/relics/" + resourcePath;
    }
    
    public static String makeRelicOutlinePath(String resourcePath) {
        return getModID() + "Resources/images/relics/outline/" + resourcePath;
    }
    
    public static String makeOrbPath(String resourcePath) {
        return getModID() + "Resources/orbs/" + resourcePath;
    }
    
    public static String makePowerPath(String resourcePath) {
        return getModID() + "Resources/images/powers/" + resourcePath;
    }
    
    public static String makeEventPath(String resourcePath) {
        return getModID() + "Resources/images/events/" + resourcePath;
    }
    
    public AbbyMod() {
        logger.info("Subscribe to BaseMod hooks");
        BaseMod.subscribe(this);
        setModID("AbbyMod");
        logger.info("Done subscribing");
        logger.info("Creating the color " + AbbyChar.Enums.FGOAb.toString());
        BaseMod.addColor(AbbyChar.Enums.FGOAb, SQRGREEN, SQRGREEN, SQRGREEN,
                SQRGREEN, SQRGREEN, SQRGREEN, SQRGREEN,
                ATTACK_FGOAb, SKILL_FGOAb, POWER_FGOAb, ENERGY_ORB_FGOAb,
                ATTACK_FGOAb_PORTRAIT, SKILL_FGOAb_PORTRAIT, POWER_FGOAb_PORTRAIT,
                ENERGY_ORB_FGOAb_PORTRAIT, CARD_ENERGY_ORB);
        
        logger.info("Done creating the color");
        
        
        logger.info("Adding mod settings");

        AbbyDefaultSettings.setProperty(ENABLE_PLACEHOLDER_SETTINGS, "FALSE");
        try {
            SpireConfig config = new SpireConfig("AbbyMod", "AbbyConfig", AbbyDefaultSettings);
            config.load();
            enableSFW = config.getBool(ENABLE_PLACEHOLDER_SETTINGS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("Done adding mod settings");
        
    }
    
    // ====== NO EDIT AREA ======
    // DON'T TOUCH THIS STUFF. IT IS HERE FOR STANDARDIZATION BETWEEN MODS AND TO ENSURE GOOD CODE PRACTICES.
    // IF YOU MODIFY THIS I WILL HUNT YOU DOWN AND DOWNVOTE YOUR MOD ON WORKSHOP
    
    public static void setModID(String ID) { // DON'T EDIT
        Gson coolG = new Gson(); // EY DON'T EDIT THIS
        //   String IDjson = Gdx.files.internal("IDCheckStringsDONT-EDIT-AT-ALL.json").readString(String.valueOf(StandardCharsets.UTF_8)); // i hate u Gdx.files
        InputStream in = AbbyMod.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json"); // DON'T EDIT THIS ETHER
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class); // OR THIS, DON'T EDIT IT
        logger.info("You are attempting to set your mod ID as: " + ID); // NO WHY
        if (ID.equals(EXCEPTION_STRINGS.DEFAULTID)) { // DO *NOT* CHANGE THIS ESPECIALLY, TO EDIT YOUR MOD ID, SCROLL UP JUST A LITTLE, IT'S JUST ABOVE
            throw new RuntimeException(EXCEPTION_STRINGS.EXCEPTION); // THIS ALSO DON'T EDIT
        } else if (ID.equals(EXCEPTION_STRINGS.DEVID)) { // NO
            modID = EXCEPTION_STRINGS.DEFAULTID; // DON'T
        } else { // NO EDIT AREA
            modID = ID; // DON'T WRITE OR CHANGE THINGS HERE NOT EVEN A LITTLE
        } // NO
        logger.info("Success! ID is " + modID); // WHY WOULD U WANT IT NOT TO LOG?? DON'T EDIT THIS.
    } // NO
    
    public static String getModID() { // NO
        return modID; // DOUBLE NO
    } // NU-UH
    
    private static void pathCheck() { // ALSO NO
        Gson coolG = new Gson(); // NNOPE DON'T EDIT THIS
        //   String IDjson = Gdx.files.internal("IDCheckStringsDONT-EDIT-AT-ALL.json").readString(String.valueOf(StandardCharsets.UTF_8)); // i still hate u btw Gdx.files
        InputStream in = AbbyMod.class.getResourceAsStream("/IDCheckStringsDONT-EDIT-AT-ALL.json"); // DON'T EDIT THISSSSS
        IDCheckDontTouchPls EXCEPTION_STRINGS = coolG.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), IDCheckDontTouchPls.class); // NAH, NO EDIT
        String packageName = AbbyMod.class.getPackage().getName(); // STILL NO EDIT ZONE
        FileHandle resourcePathExists = Gdx.files.internal(getModID() + "Resources"); // PLEASE DON'T EDIT THINGS HERE, THANKS
        if (!modID.equals(EXCEPTION_STRINGS.DEVID)) { // LEAVE THIS EDIT-LESS
            if (!packageName.equals(getModID())) { // NOT HERE ETHER
                throw new RuntimeException(EXCEPTION_STRINGS.PACKAGE_EXCEPTION + getModID()); // THIS IS A NO-NO
            } // WHY WOULD U EDIT THIS
            if (!resourcePathExists.exists()) { // DON'T CHANGE THIS
                throw new RuntimeException(EXCEPTION_STRINGS.RESOURCE_FOLDER_EXCEPTION + getModID() + "Resources"); // NOT THIS
            }// NO
        }// NO
    }// NO
    
    // ====== YOU CAN EDIT AGAIN ======
    

    @SuppressWarnings("unused")
    public static void initialize() {
        logger.info("========================= Initializing Abigail Mod. =========================");
        AbbyMod abbyMod = new AbbyMod();
        logger.info("========================= /Abigail ready for action./ =========================");
    }
    
    @Override
    public void receiveEditCharacters() {
        logger.info("Beginning to edit characters. " + "Add " + AbbyChar.Enums.Ab_Foreigner.toString());
        
        BaseMod.addCharacter(new AbbyChar("the Foreigner", AbbyChar.Enums.Ab_Foreigner),
                Abby_BUTTON, Abby_PORTRAIT, AbbyChar.Enums.Ab_Foreigner);
        
        receiveEditPotions();
        logger.info("Added " + AbbyChar.Enums.Ab_Foreigner.toString());
    }

    @Override
    public void receivePostInitialize() {
        logger.info("Loading badge image and mod options");
        Texture badgeTexture = TextureLoader.getTexture(BADGE_IMAGE);
        ModPanel settingsPanel = new ModPanel();
        ModLabeledToggleButton enableNormalsButton = new ModLabeledToggleButton("Enable clean mode.",
                350.0f, 700.0f, Settings.CREAM_COLOR, FontHelper.charDescFont,
                enableSFW,
                settingsPanel,
                (label) -> {},
                (button) -> {
            
            enableSFW = button.enabled;
            try {
                SpireConfig config = new SpireConfig("AbbyMod", "AbbyConfig", AbbyDefaultSettings);
                config.setBool(ENABLE_PLACEHOLDER_SETTINGS, enableSFW);
                config.save();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        settingsPanel.addUIElement(enableNormalsButton);
        
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);

        
        // =============== EVENTS =================
        
        // This event will be exclusive to the City (act 2). If you want an event that's present at any
        // part of the game, simply don't include the dungeon ID
        // If you want to have a character-specific event, look at slimebound (CityRemoveEventPatch).
        // Essentially, you need to patch the game and say "if a player is not playing my character class, remove the event from the pool"
        //BaseMod.addEvent(CLASSNAME.ID, CLASSNAME.class, ACTNAME.ID);
        
        // =============== /EVENTS/ =================
        logger.info("Done loading badge Image and mod options");
    }

    public void receiveEditPotions() {
        logger.info("Beginning to edit potions");
        
        logger.info("Done editing potions");
    }

    @Override
    public void receiveEditRelics() {
        logger.info("Adding relics");
        BaseMod.addRelicToCustomPool(new Salem_Diary(), AbbyChar.Enums.FGOAb);
        BaseMod.addRelicToCustomPool(new YogBlessing(), AbbyChar.Enums.FGOAb);
        BaseMod.addRelicToCustomPool(new CosmoBattery(), AbbyChar.Enums.FGOAb);
        BaseMod.addRelicToCustomPool(new MetalizedInsanity(), AbbyChar.Enums.FGOAb);
        logger.info("Done adding relics!");
    }

    @Override
    public void receiveEditCards() {
        logger.info("Adding variables");
        pathCheck();
        logger.info("Add variabls");
        BaseMod.addDynamicVariable(new DefaultCustomVariable());
        BaseMod.addDynamicVariable(new DefaultSecondMagicNumber());
        logger.info("Adding cards");
        BaseMod.addCard(new Dabigail());
        BaseMod.addCard(new Abby_NP());
        BaseMod.addCard(new Ab_Defend());
        BaseMod.addCard(new Ab_Strike());
        BaseMod.addCard(new AdultWords());
        BaseMod.addCard(new ChocoKey());
        BaseMod.addCard(new ConsumeFlesh());
        BaseMod.addCard(new DemonPancake());
        BaseMod.addCard(new EyesOfMadness());
        BaseMod.addCard(new FamiliarShield());
        BaseMod.addCard(new FleetingSanity());
        BaseMod.addCard(new ForeheadBeam());
        BaseMod.addCard(new Keyblade());
        BaseMod.addCard(new LargeTentacle());
        BaseMod.addCard(new LossOfSanity());
        BaseMod.addCard(new OldOneLimb());
        BaseMod.addCard(new SanityCheck());
        BaseMod.addCard(new SavingThrow());
        BaseMod.addCard(new TeddyBear());
        BaseMod.addCard(new TentacleSwarm());
        BaseMod.addCard(new WitchTrial());
        BaseMod.addCard(new ImpossibleGeometry());
        BaseMod.addCard(new PapaProtection());
        BaseMod.addCard(new Thflth());
        BaseMod.addCard(new Ygna());
        BaseMod.addCard(new PrayerOfFaith());
        BaseMod.addCard(new Takeuchi());
        BaseMod.addCard(new Kinoko());
        BaseMod.addCard(new Kuroboshi());
        BaseMod.addCard(new Hitomi());
        BaseMod.addCard(new Meteo());
        BaseMod.addCard(new CommandSpell());
        BaseMod.addCard(new FlyingMascot());
        BaseMod.addCard(new SweetPancakes());
        BaseMod.addCard(new RecklessSlam());
        BaseMod.addCard(new BadGirl());
        BaseMod.addCard(new SilverKey());
        BaseMod.addCard(new ExposeNavel());
        BaseMod.addCard(new LiftSkirt());
        BaseMod.addCard(new InsaneStrike());
        BaseMod.addCard(new AlignStars());
        BaseMod.addCard(new BlackKey());
        BaseMod.addCard(new MysteryKey());
        BaseMod.addCard(new SlimyBody());
        BaseMod.addCard(new VoidWhispers());
        BaseMod.addCard(new VoidPriest());
        BaseMod.addCard(new VoidHeretic());
        BaseMod.addCard(new PantyShot());
        BaseMod.addCard(new AbsoluteCorruption());
        BaseMod.addCard(new MindCleanse());
        BaseMod.addCard(new PowerGamer());
        BaseMod.addCard(new MeltBrain());
        BaseMod.addCard(new HeroCreationA());
        BaseMod.addCard(new WorldStrongest());
        BaseMod.addCard(new HolidayWishes());
        BaseMod.addCard(new LifeSentence());
        BaseMod.addCard(new PreciousMemories());
        BaseMod.addCard(new SuperMuscular());
        BaseMod.addCard(new WormHole());
        BaseMod.addCard(new CosmicSpark());
        BaseMod.addCard(new Ab_PuppyEyes());
        BaseMod.addCard(new Ab_DarkestPact());
        BaseMod.addCard(new Ab_OldOneTrigger());
        BaseMod.addCard(new Ab_SwirlOfMadness());
        BaseMod.addCard(new Ab_FiveStars());
        BaseMod.addCard(new AceInTheHole());
        BaseMod.addCard(new FarAwayFriend());
        BaseMod.addCard(new FatalEntropy());
        BaseMod.addCard(new AttoSecond());
        BaseMod.addCard(new StarRadiance());
        BaseMod.addCard(new GudaGudaParticle());
        BaseMod.addCard(new Memocide());
        BaseMod.addCard(new CloneArmy());
        BaseMod.addCard(new ExposedCore());
        BaseMod.addCard(new EquivalentExchange());








        logger.info("Making sure the cards are unlocked.");
        UnlockTracker.unlockCard(Dabigail.ID);
        UnlockTracker.unlockCard(Abby_NP.ID);
        UnlockTracker.unlockCard(Ab_Defend.ID);
        UnlockTracker.unlockCard(Ab_Strike.ID);
        UnlockTracker.unlockCard(AdultWords.ID);
        UnlockTracker.unlockCard(ChocoKey.ID);
        UnlockTracker.unlockCard(ConsumeFlesh.ID);
        UnlockTracker.unlockCard(DemonPancake.ID);
        UnlockTracker.unlockCard(EyesOfMadness.ID);
        UnlockTracker.unlockCard(FamiliarShield.ID);
        UnlockTracker.unlockCard(FleetingSanity.ID);
        UnlockTracker.unlockCard(ForeheadBeam.ID);
        UnlockTracker.unlockCard(Keyblade.ID);
        UnlockTracker.unlockCard(LargeTentacle.ID);
        UnlockTracker.unlockCard(LossOfSanity.ID);
        UnlockTracker.unlockCard(OldOneLimb.ID);
        UnlockTracker.unlockCard(SanityCheck.ID);
        UnlockTracker.unlockCard(SavingThrow.ID);
        UnlockTracker.unlockCard(TeddyBear.ID);
        UnlockTracker.unlockCard(TentacleSwarm.ID);
        UnlockTracker.unlockCard(WitchTrial.ID);
        UnlockTracker.unlockCard(ImpossibleGeometry.ID);
        UnlockTracker.unlockCard(PapaProtection.ID);
        UnlockTracker.unlockCard(Thflth.ID);
        UnlockTracker.unlockCard(Ygna.ID);
        UnlockTracker.unlockCard(PrayerOfFaith.ID);
        UnlockTracker.unlockCard(Takeuchi.ID);
        UnlockTracker.unlockCard(Kinoko.ID);
        UnlockTracker.unlockCard(Kuroboshi.ID);
        UnlockTracker.unlockCard(Hitomi.ID);
        UnlockTracker.unlockCard(Meteo.ID);
        UnlockTracker.unlockCard(CommandSpell.ID);
        UnlockTracker.unlockCard(FlyingMascot.ID);
        UnlockTracker.unlockCard(SweetPancakes.ID);
        UnlockTracker.unlockCard(RecklessSlam.ID);
        UnlockTracker.unlockCard(BadGirl.ID);
        UnlockTracker.unlockCard(SilverKey.ID);
        UnlockTracker.unlockCard(ExposeNavel.ID);
        UnlockTracker.unlockCard(LiftSkirt.ID);
        UnlockTracker.unlockCard(InsaneStrike.ID);
        UnlockTracker.unlockCard(AlignStars.ID);
        UnlockTracker.unlockCard(BlackKey.ID);
        UnlockTracker.unlockCard(MysteryKey.ID);
        UnlockTracker.unlockCard(SlimyBody.ID);
        UnlockTracker.unlockCard(VoidWhispers.ID);
        UnlockTracker.unlockCard(VoidPriest.ID);
        UnlockTracker.unlockCard(VoidHeretic.ID);
        UnlockTracker.unlockCard(PantyShot.ID);
        UnlockTracker.unlockCard(AbsoluteCorruption.ID);
        UnlockTracker.unlockCard(MindCleanse.ID);
        UnlockTracker.unlockCard(PowerGamer.ID);
        UnlockTracker.unlockCard(MeltBrain.ID);
        UnlockTracker.unlockCard(HeroCreationA.ID);
        UnlockTracker.unlockCard(WorldStrongest.ID);
        UnlockTracker.unlockCard(HolidayWishes.ID);
        UnlockTracker.unlockCard(LifeSentence.ID);
        UnlockTracker.unlockCard(PreciousMemories.ID);
        UnlockTracker.unlockCard(SuperMuscular.ID);
        UnlockTracker.unlockCard(WormHole.ID);
        UnlockTracker.unlockCard(CosmicSpark.ID);
        UnlockTracker.unlockCard(Ab_PuppyEyes.ID);
        UnlockTracker.unlockCard(Ab_DarkestPact.ID);
        UnlockTracker.unlockCard(Ab_OldOneTrigger.ID);
        UnlockTracker.unlockCard(Ab_SwirlOfMadness.ID);
        UnlockTracker.unlockCard(Ab_FiveStars.ID);
        UnlockTracker.unlockCard(AceInTheHole.ID);
        UnlockTracker.unlockCard(FarAwayFriend.ID);
        UnlockTracker.unlockCard(FatalEntropy.ID);
        UnlockTracker.unlockCard(AttoSecond.ID);
        UnlockTracker.unlockCard(StarRadiance.ID);
        UnlockTracker.unlockCard(GudaGudaParticle.ID);
        UnlockTracker.unlockCard(Memocide.ID);
        UnlockTracker.unlockCard(CloneArmy.ID);
        UnlockTracker.unlockCard(EquivalentExchange.ID);
        UnlockTracker.unlockCard(ExposedCore.ID);








        logger.info("Done adding cards!");
    }
    @Override
    public void receiveEditStrings() {
        logger.info("You seeing this?");
        logger.info("Beginning to edit strings for mod with ID: " + getModID());
        
        // CardStrings
        BaseMod.loadCustomStringsFile(CardStrings.class,
                getModID() + "Resources/localization/eng/AbbyMod-Card-Strings.json");
        
        // PowerStrings
        BaseMod.loadCustomStringsFile(PowerStrings.class,
                getModID() + "Resources/localization/eng/AbbyMod-Power-Strings.json");
        
        // RelicStrings
        BaseMod.loadCustomStringsFile(RelicStrings.class,
                getModID() + "Resources/localization/eng/AbbyMod-Relic-Strings.json");

        // CharacterStrings
        BaseMod.loadCustomStringsFile(CharacterStrings.class,
                getModID() + "Resources/localization/eng/AbbyMod-Character-Strings.json");
        /*switch (Settings.language) {
            case KOR:
                // CardStrings
                BaseMod.loadCustomStringsFile(CardStrings.class,
                        getModID() + "Resources/localization/kor/AbbyMod-Card-Strings.json");

                // PowerStrings
                BaseMod.loadCustomStringsFile(PowerStrings.class,
                        getModID() + "Resources/localization/kor/AbbyMod-Power-Strings.json");

                // RelicStrings
                BaseMod.loadCustomStringsFile(RelicStrings.class,
                        getModID() + "Resources/localization/kor/AbbyMod-Relic-Strings.json");

                // CharacterStrings
                BaseMod.loadCustomStringsFile(CharacterStrings.class,
                        getModID() + "Resources/localization/kor/AbbyMod-Character-Strings.json");
                break;
            }
         */
        logger.info("Done editing strings");
    }

    @Override
    public void receiveEditKeywords() {
        Gson gson = new Gson();
        String json = Gdx.files.internal(getModID() + "Resources/localization/eng/AbbyMod-Keyword-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        com.evacipated.cardcrawl.mod.stslib.Keyword[] keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);
        
        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(getModID().toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
            }
        }
        /*switch (Settings.language) {
            case KOR:
                json = Gdx.files.internal(getModID() + "Resources/localization/kor/AbbyMod-Keyword-Strings.json").readString(String.valueOf(StandardCharsets.UTF_8));
                keywords = gson.fromJson(json, com.evacipated.cardcrawl.mod.stslib.Keyword[].class);

                if (keywords != null) {
                    for (Keyword keyword : keywords) {
                        BaseMod.addKeyword(getModID().toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
                    }
                }
        }*/
    }

    public static String makeID(String idText) {
        return getModID() + ":" + idText;
    }

    @Override
    public void receivePostDeath() {
        StarRadiance.resetCounter();
    }
}
