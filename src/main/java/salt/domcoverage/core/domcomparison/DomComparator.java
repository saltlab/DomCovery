package salt.domcoverage.core.domcomparison;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.custommonkey.xmlunit.Difference;
import org.w3c.dom.Document;

import salt.domcoverage.core.domcomparison.clustering.DataClustering;

import com.apporiented.algorithm.clustering.Cluster;
import com.crawljax.oraclecomparator.comparators.PlainStructureComparator;
import com.crawljax.util.DOMComparer;
import com.crawljax.util.DomUtils;

public class DomComparator {
	
	public List<Difference> differences(String dom1,String dom2){
		List<Difference> differences= new ArrayList<Difference>();
		try {
			String dom11=new PlainStructureComparator(false).normalize(dom1);
			String dom22=new PlainStructureComparator(false).normalize(dom2);
			Document dom1doc = DomUtils.asDocument(dom11);dom1doc.getElementsByTagName("*").getLength();
			Document dom2doc = DomUtils.asDocument(dom22);
			DOMComparer dc= new DOMComparer(dom1doc, dom2doc);
			differences = dc.compare();
			//System.out.println("difference size: "+ differences.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return differences; 
		
	}
	public double[][] extractDistances(ArrayList<File> domFiles)  {
		double[][] distances= new double[domFiles.size()][domFiles.size()] ;
		try {
		File difffile = new File("differences.csv");
		FileUtils.deleteQuietly(difffile);
		for (int i = 0; i < domFiles.size(); i++) {
			for (int j = 0; j < domFiles.size(); j++) {
				List<Difference> differences;
				
					differences = differences(FileUtils.readFileToString(domFiles.get(i)), FileUtils.readFileToString(domFiles.get(j)));
				
				distances[i][j]=differences.size();
				String stringtoWrite = domFiles.get(i).getName()+","+domFiles.get(j).getName()+","+differences.size();
				//System.out.println(stringtoWrite);
				FileUtils.writeStringToFile(difffile,stringtoWrite+"\n",true);
			}
			FileUtils.writeStringToFile(difffile,"\n",true);
		}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return distances;
	}
	public ArrayList<File> getDomFiles(String folder) {
		Collection<File> files = FileUtils.listFiles(new File(folder), null, false);
		ArrayList<File> domFiles= new ArrayList<File>();
		for (File file : files) {
			if (file.getName().contains("DOM"))
				domFiles.add(file);
		}
		return domFiles;
	}

}
