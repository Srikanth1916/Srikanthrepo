package io.skyhive.veneer.typeahead.dto;

/**
 * @author krishna
 * @created 12/01/22
 * @project skyhive-veeneer
 */
    public enum DictionaryType
    {
        None(0),
        Country(1), //CountryDictionaryEntity
        Skill(2), //SkillToolDictionaryEntity
        //"Tools are stored as Skills with IsTool = true"
        Tool(3), // moved to skills
        Requirement(4), //DictionaryWithComplexityEntity
        StudyType(5), //DictionaryWithSkillsEntity
        DegreeType(6), //DictionaryWithComplexityEntity
        Hobby(7), //DictionaryWithComplexityEntity
        Training(8), //DictionaryWithSkillsEntity
        Certification(9), //DictionaryWithSkillsEntity
        Institution(10), //DictionaryWithComplexityEntity
        Gender(11), //DictionaryValueEntity
        Industries(12),
        Sectors(13),
        WorkType(14), //DictionaryValueEntity
        Occupation(15),
        HouseHold(16),
        CompanySize(17),
        Age(18),
        JobTitle(19), //DictionaryWithSkillsEntity
        JobContext(20),
        ClusteredTitle(21),//DictionaryValueEntity
        SkillCategory(22); //skills can have 'clients' which can give it
                // custom attributes (name, category, complexity)
        int value;
        DictionaryType(int value){
            this.value = value;
        }

        public int getValue(){
            return value;
        }
    }
