package io.github.darealturtywurty.turtychemistry.core.init;

import io.github.darealturtywurty.turtychemistry.TurtyChemistry;
import io.github.darealturtywurty.turtychemistry.common.item.ChemistryItem;
import io.github.darealturtywurty.turtylib.core.init.AbstractInit;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PlaceOnWaterBlockItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit extends AbstractInit {
    
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS,
        TurtyChemistry.MODID);
    
    public static final RegistryObject<ChemistryItem> HYDROGEN = ITEMS.register("hydrogen",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> HELIUM = ITEMS.register("helium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> LITHIUM = ITEMS.register("lithium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> BERYLLIUM = ITEMS.register("beryllium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> BORON = ITEMS.register("boron",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> CARBON = ITEMS.register("carbon",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> NITROGEN = ITEMS.register("nitrogen",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> OXYGEN = ITEMS.register("oxygen",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> FLUORINE = ITEMS.register("fluorine",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> NEON = ITEMS.register("neon",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> SODIUM = ITEMS.register("sodium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> MAGNESIUM = ITEMS.register("magnesium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> ALUMINIUM = ITEMS.register("aluminium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> SILICON = ITEMS.register("silicon",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> PHOSPHORUS = ITEMS.register("phosphorus",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> SULFUR = ITEMS.register("sulfur",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> CHLORINE = ITEMS.register("chlorine",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> ARGON = ITEMS.register("argon",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> POTASSIUM = ITEMS.register("potassium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> CALCIUM = ITEMS.register("calcium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> SCANDIUM = ITEMS.register("scandium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> TITANIUM = ITEMS.register("titanium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> VANADIUM = ITEMS.register("vanadium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> CHROMIUM = ITEMS.register("chromium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> MANGANESE = ITEMS.register("manganese",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> IRON = ITEMS.register("iron",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> COBALT = ITEMS.register("cobalt",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> NICKEL = ITEMS.register("nickel",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> COPPER = ITEMS.register("copper",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> ZINC = ITEMS.register("zinc",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> GALLIUM = ITEMS.register("gallium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> GERMANIUM = ITEMS.register("germanium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> ARSENIC = ITEMS.register("arsenic",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> SELENIUM = ITEMS.register("selenium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> BROMINE = ITEMS.register("bromine",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> KRYPTON = ITEMS.register("krypton",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> RUBIDIUM = ITEMS.register("rubidium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> STRONTIUM = ITEMS.register("strontium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> YTTRIUM = ITEMS.register("yttrium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> ZIRCONIUM = ITEMS.register("zirconium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> NIOBIUM = ITEMS.register("niobium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> MOLYDBENUM = ITEMS.register("molydbenum",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> TECHNETIUM = ITEMS.register("technetium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> RUTHENIUM = ITEMS.register("ruthenium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> RHODIUM = ITEMS.register("rhodium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> PALLADIUM = ITEMS.register("palladium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> SILVER = ITEMS.register("silver",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> CADMIUM = ITEMS.register("cadmium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> INDIUM = ITEMS.register("indium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> TIN = ITEMS.register("tin",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> ANTIMONY = ITEMS.register("antimony",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> TELLURIUM = ITEMS.register("tellurium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> IODINE = ITEMS.register("iodine",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> XENON = ITEMS.register("xenon",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> CESIUM = ITEMS.register("cesium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> BARIUM = ITEMS.register("barium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> LANTHANUM = ITEMS.register("lanthanum",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> CERIUM = ITEMS.register("cerium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> PRASEODYMIUM = ITEMS.register("praseodymium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> NEODYMIUM = ITEMS.register("neodymium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> PROMETHIUM = ITEMS.register("promethium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> SAMARIUM = ITEMS.register("samarium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> EUROPIUM = ITEMS.register("europium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> GADOLINIUM = ITEMS.register("gadolinium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> TERBIUM = ITEMS.register("terbium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> DYSPROSIUM = ITEMS.register("dysprosium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> HOLMIUM = ITEMS.register("holmium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> ERBIUM = ITEMS.register("erbium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> THULIUM = ITEMS.register("thulium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> YTTERBIUM = ITEMS.register("ytterbium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> LUTETIUM = ITEMS.register("lutetium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> HAFNIUM = ITEMS.register("hafnium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> TANTALUM = ITEMS.register("tantalum",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> TUNGSTEN = ITEMS.register("tungsten",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> RHENIUM = ITEMS.register("rhenium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> OSMIUM = ITEMS.register("osmium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> IRIDIUM = ITEMS.register("iridium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> PLATINUM = ITEMS.register("platinum",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> GOLD = ITEMS.register("gold",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> MERCURY = ITEMS.register("mercury",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> THALLIUM = ITEMS.register("thallium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> LEAD = ITEMS.register("lead",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> BISMUTH = ITEMS.register("bismuth",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> POLONIUM = ITEMS.register("polonium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> ASTATINE = ITEMS.register("astatine",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> RADON = ITEMS.register("radon",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> FRANCIUM = ITEMS.register("francium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> RADIUM = ITEMS.register("radium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> ACTINIUM = ITEMS.register("actinium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> THORIUM = ITEMS.register("thorium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> PROTACTINIUM = ITEMS.register("protactinium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> URANIUM = ITEMS.register("uranium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> NEPTUNIUM = ITEMS.register("neptunium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> PLUTONIUM = ITEMS.register("plutonium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> AMERICIUM = ITEMS.register("americium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> CURIUM = ITEMS.register("curium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> BERKELLIUM = ITEMS.register("berkellium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> CALIFORNIUM = ITEMS.register("californium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> EINSTEINIUM = ITEMS.register("einsteinium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> FERMIUM = ITEMS.register("fermium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> MENDELEVIUM = ITEMS.register("mendelevium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> NOBELLIUM = ITEMS.register("nobellium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> LAWRENCIUM = ITEMS.register("lawrencium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> RUTHERFORDIUM = ITEMS.register("rutherfordium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> DUBNIUM = ITEMS.register("dubnium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> SEABROGIUM = ITEMS.register("seabrogium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> BOHRIUM = ITEMS.register("bohrium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> HASSIUM = ITEMS.register("hassium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> MEITNERIUM = ITEMS.register("meitnerium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> DARMSTADTIUM = ITEMS.register("darmstadtium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> ROENTGENIUM = ITEMS.register("roentgenium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> COPERNICIUM = ITEMS.register("copernicium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> NIHONIUM = ITEMS.register("nihonium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> FLEROVIUM = ITEMS.register("flerovium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> MOSCOVIUM = ITEMS.register("moscovium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> LIVERMORIUM = ITEMS.register("livermorium",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> TENNESSINE = ITEMS.register("tennessine",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> OGANESSON = ITEMS.register("oganesson",
        () -> new ChemistryItem(makeItemProperties()));
    
    public static final RegistryObject<ChemistryItem> REFINARY_GAS = ITEMS.register("refinary_gas",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> PETROL = ITEMS.register("petrol",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> NAPHTHA = ITEMS.register("naphtha",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> KEROSINE = ITEMS.register("kerosine",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> DIESEL = ITEMS.register("diesel",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> LUBRICATING_OIL = ITEMS.register("lubricating_oil",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> FUEL_OIL = ITEMS.register("fuel_oil",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> BITUMEN = ITEMS.register("bitumen",
        () -> new ChemistryItem(makeItemProperties()));
    
    public static final RegistryObject<ChemistryItem> AMMONIA = ITEMS.register("ammonia",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> HYDROCHLORIC_ACID = ITEMS.register("hydrochloric_acid",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> ISOPROPYL_ALCOHOL = ITEMS.register("isopropyl_alcohol",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> SULFURIC_ACID = ITEMS.register("sulfuric_acid",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> NITRIC_ACID = ITEMS.register("nitric_acid",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> SODIUM_HYDROXIDE = ITEMS.register("sodium_hydroxide",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> METHANE = ITEMS.register("methane",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> CARBON_MONOXIDE = ITEMS.register("carbon_monoxide",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> PHOSPHORIC_ACID = ITEMS.register("phosphoric_acid",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> CHRYSOBERYL = ITEMS.register("chrysoberyl",
        () -> new ChemistryItem(makeItemProperties()));
    
    public static final RegistryObject<ChemistryItem> BALLOON = ITEMS.register("balloon",
        () -> new ChemistryItem(makeItemProperties()));
    public static final RegistryObject<ChemistryItem> TEST_TUBE = ITEMS.register("test_tube",
        () -> new ChemistryItem(makeItemProperties()));
    
    public static ChemistryItem.Builder makeItemProperties() {
        return new ChemistryItem.Builder(new ChemistryItem.Properties().tab(TurtyChemistry.TAB));
    }
}
