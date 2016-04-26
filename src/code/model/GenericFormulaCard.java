package code.model;

/**
 * @author ccaba_000
 *
 */
public class GenericFormulaCard {
	
	/**
	 * 
	 */
	protected int ingredient1;
	/**
	 * 
	 */
	protected int ingredient2;
	/**
	 * 
	 */
	protected int ingredient3;
	
	/**
	 * 
	 */
	protected String _ingredient1;
	/**
	 * 
	 */
	protected String _ingredient2;
	/**
	 * 
	 */
	protected String _ingredient3;

	/**
	 * @param i1
	 * @param i2
	 * @param i3
	 */
	public GenericFormulaCard(int i1, int i2, int i3){
		ingredient1 = i1;
		ingredient2 = i2;
		ingredient3 = i3;		
	}
	
	
//01	"Crab Apple";	
//02	"Pine Cone";	
//03	"Oak Leaf";
//04		"Oil of Black Slugs";
//05	"Four-leaf Clover";
//06		"Garlic";
//07		"Raven's Feather";
//08		"Henbane";
//09		"Spider";
//10		"Skull";
//11		"Magic Wand Made of Blindworm";
//12		"Quartz";
//13		"Toad";
//14		"Fire Salamander";
//15		"Weasel Spit";
//16		"Silver Thistle";
//17		"Snake";
//18		"Emerald";
//19		"Root of Mandrake";
//20		"Black Rooster";
//25		"Berries of Mistletoe";
	
	
	/**
	 * @param key
	 * @return
	 */
	public boolean isIngredient(int key){
		return key == ingredient1 || key == ingredient2 || key == ingredient3;
	}
	
	
}
