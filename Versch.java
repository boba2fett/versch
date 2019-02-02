import java.util.*;
public class Versch
{
    public Versch()
    {

    }
    public String letzterSchluessel="";
    public int letzteVerschInt=0;
    private int ggT(int n,int m)
    {
        if(n>m)
        {
            n=n+m;
            m=n-m;
            n=n-m;
        }
        m=m%n;//eigentlich r aber m wird nicht mehr benötigt
        if(m==0)
        {
            return n;
        }
        return ggT(n,m);
    }
    
    public String caeser(String klart,int versch)
    {
        klart=stringsauber(klart);
        while(versch<-26){versch+=26;}
        while(versch>26){versch-=26;}
        int length=klart.length();
        String geheimt="";
        for (int i=0;i<length;i++){
            char buchs=klart.charAt(i);
            buchs+=versch;
            if(buchs<65){buchs+=26;}
            if(buchs>90){buchs-=26;}
            geheimt+=buchs;
        }
        return geheimt;
    }

    public String atbash(String klart)
    {
        klart=stringsauber(klart);
        int length=klart.length();
        String geheimt="";
        for (int i=0;i<length;i++){
            char buchs=klart.charAt(i);
            int ch=90-buchs+65;
            buchs=(char)ch;
            geheimt+=buchs;
        }
        return geheimt;
    }
    
    public String vigener(String klart,String key)
    {
        klart=stringsauber(klart);
        int length=klart.length();
        key=stringsauber(key);
        int lengthk=key.length();
        String geheimt="";
        for (int i=0;i<length;i++){
            char buchs=klart.charAt(i);
            buchs+=key.charAt(i%lengthk)-65;
            if(buchs<65){buchs+=26;}
            if(buchs>90){buchs-=26;}
            geheimt+=buchs;
        }
        return geheimt;
    }
    
    public String vigenerent(String geheimt,String key)
    {
        geheimt=stringsauber(geheimt);
        int length=geheimt.length();
        key=stringsauber(key);
        int lengthk=key.length();
        String klart="";
        for (int i=0;i<length;i++){
            char buchs=geheimt.charAt(i);
            int ch=buchs-key.charAt(i%lengthk)+65;
            buchs=(char)ch;
            if(buchs<65){buchs+=26;}
            if(buchs>90){buchs-=26;}
            klart+=buchs;
        }
        return klart;
    }
    
    public String autokey(String klart,String key)
    {
        klart=stringsauber(klart);
        int length=klart.length();
        key=stringsauber(key);
        key+=klart;
        String geheimt="";
        for (int i=0;i<length;i++){
            char buchs=klart.charAt(i);
            buchs+=key.charAt(i)-65;
            if(buchs<65){buchs+=26;}
            if(buchs>90){buchs-=26;}
            geheimt+=buchs;
        }
        return geheimt;
    }
    
    public String autokeyent(String geheimt,String key)
    {
        geheimt=stringsauber(geheimt);
        int length=geheimt.length();
        key=stringsauber(key);
        String klart="";
        for (int i=0;i<length;i++){
            char buchs=geheimt.charAt(i);
            int ch=buchs-key.charAt(i)+65;
            buchs=(char)ch;
            if(buchs<65){buchs+=26;}
            if(buchs>90){buchs-=26;}
            key+=buchs;
            klart+=buchs;
        }
        return klart;
    }
    
    public String caeserhack(String geheimt)
    {
        geheimt=stringsauber(geheimt);
        int versch=-'E'+haeufigsterChar(geheimt);
        letzteVerschInt=versch;
        return caeser(geheimt,versch);
    }
    
    public char haeufigsterChar(String text)
    {
        text=stringsauber(text);
        char haeufigster=0;
        int haeufigkeit=0;
        for(char i=65;i<=90;i++)
        {
            int anzahl=zaehle(text,i);
            if(anzahl>haeufigkeit){haeufigster=i;
            haeufigkeit=anzahl;}
        }
        return haeufigster;
    }
    
    private int zaehle(String text,char buch)
    {
        int zaehl=0;
        for(int i=0;i<text.length();i++)
        {
            if(text.charAt(i)==buch){zaehl++;}
        }
        return zaehl;
    }
    
    
    public String stringsauber(String text)
    {
        String neu="";
        text=text.toUpperCase();
        for (int i=0;i<text.length();i++){
            if(text.charAt(i)>=65&&text.charAt(i)<=90){neu+=text.charAt(i);}
            else
            {
                if((int)text.charAt(i) == 'Ä')
                {
                    neu+="AE";
                }
                if((int)text.charAt(i) == 'Ö')
                {
                    neu="OE";
                }
                if((int)text.charAt(i) == 'Ü')
                {
                    neu+="UE";
                }
                if((int)text.charAt(i) == 'ß')
                {
                    neu+="SS";
                }
            }
        }
        return neu;
    }
    
    public String vigenerhack(String geheimt,int keylength)
    {
        geheimt=stringsauber(geheimt);
        String sarr[]=new String[keylength];//StringARRay
        for(int i=0;i<geheimt.length();i++)
        {
            sarr[i%keylength]+=geheimt.charAt(i);
        }
        String key="";
        for(int i=0;i<keylength;i++)
        {
            int anhang=-'E'+haeufigsterChar(sarr[i])+65;
            if(anhang<65){anhang+=26;}
            if(anhang>90){anhang-=26;}
            key+=(char)anhang;
        }
        letzterSchluessel=key;
        return vigenerent(geheimt,key);
    }
    
    public String vigenerhack(String geheimt)
    {
        geheimt=stringsauber(geheimt);
        int length=geheimt.length();
        int keylength=0;
        String subs="";
        int index;
        for(int i=0;i+3<length;i++)
        {
            subs=geheimt.substring(i,i+3);
            index=geheimt.indexOf(subs,i+3);//i+1 weil er sonst nochmal das gleiche findet
            if(index!=-1)
            {
                if(keylength!=0){
                    if(ggT(keylength,index-i)!=1)/*falls etwas nicht reinpasst*/{keylength=ggT(keylength,index-i);}
                    }//index-i ist die Entfernung
                else{keylength=index-i;}
            }
        }
        return vigenerhack(geheimt,keylength);
    }
    
    public String string_eingabe()
    {
        Scanner in =new Scanner(System.in);
        String input = in.nextLine();
        return input;
    }
}
