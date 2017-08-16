import java.util.*;
public class Huffman {
	static int sum;
	static ArrayList<Base> finalResult = new ArrayList<Base> ();
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		String[] chars = new String[n];
		ArrayList<Base> base = new ArrayList<Base> ();
		String input = null;
		int i = 0;
		while(i<n) {
		    
			chars[i] = sc.next();
			base.add(new Base(chars[i],Integer.parseInt(sc.next())));
			i++;
		}
		Huffman hf = new Huffman();
		Base root = null;
		while(!base.isEmpty()) {
			if(base.size() == 1) break;
			Collections.sort(base);
			Base a = base.remove(0);
			a.setCode("0");
			Base b = base.remove(0);
			b.setCode("1");
			Base c = hf.computeFrequency(a,b);
			root = c;
			base.add(c);
		}
		String str = "";
		hf.printLevels(root,str);
		Collections.sort(finalResult, new Base());
		for(int k = 0;k<finalResult.size();k++) {
		System.out.println(finalResult.get(k));
	}
		for (int j = 0;j<finalResult.size();j++ ) {
			sum+= finalResult.get(j).getCode().length() * finalResult.get(j).getFrequency();
		}
		System.out.println(sum);
	}

	public Base computeFrequency(Base a, Base b) {
		String vertex = a.getVertex()+""+b.getVertex();
		int frequency = a.getFrequency()+b.getFrequency();
		Base c = new Base(vertex,frequency);
		c.setLeft(a);
		c.setRight(b);
		return c;
	}

	public void printLevels(Base root,String stb)
    {
        if(root == null) return;
        stb = stb + root.getCode();
        if(root.getLeft()==null && root.getRight()==null) {
        	root.setCode(stb);
        	finalResult.add(root);
        }
        printLevels(root.getLeft(),stb);
        printLevels(root.getRight(),stb);

    }
}

	