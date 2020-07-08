public class NameTest 
{
    public static void main(String[] args) 
    {
        Name derek = new Name("Derek", "Greene");
        ImmutableName derekI = derek.getImmutable();
        ListInterface<ImmutableName> nameList = new AList<>();
        nameList.add(derekI);
        
        
        ImmutableName lila = new ImmutableName("Lila", "Bleu");
        Name changer = lila.getMutable();
        changer.setLast("Greene");
        ImmutableName unchanger = changer.getImmutable();
    }    
}
