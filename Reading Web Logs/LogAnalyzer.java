
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     private HashMap<String, Integer> counts;
     private HashMap<String, ArrayList<String>> ipsForDays;

     public LogAnalyzer() {
         records = new ArrayList<LogEntry>();
         counts = new HashMap<String, Integer>();
         ipsForDays = new HashMap<String, ArrayList<String>>();
     }
        
     public void readFile(String filename) {
         FileResource fr = new FileResource();
         for(String line : fr.lines()){
             LogEntry log = WebLogParser.parseEntry(line);
             records.add(log);
            }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     public int countUniqueIPs (){
         ArrayList<String> uniqueIps = new ArrayList<String>();
         for(LogEntry le : records){
             String ip = le.getIpAddress();
             if(!uniqueIps.contains(ip)){
                uniqueIps.add(ip);
                }
         }
         return uniqueIps.size();
     }
     public ArrayList<String> uniqueIPVisitsOnDay (String someday){
        ArrayList<String> uniqueIPsVisit = new ArrayList<String>();
        for(LogEntry le : records){
            Date time = le.getAccessTime();
            String day = time.toString();
            day=day.substring(4,10);
            if(day.equals(someday)){
                String ipAddress = le.getIpAddress();
                if(!uniqueIPsVisit.contains(ipAddress)){
                    uniqueIPsVisit.add(ipAddress);
                }
            }
        }
        System.out.println("Size of an array list is " + uniqueIPsVisit.size());
        return uniqueIPsVisit;
     }
     
     public int countUniqueIPsInRange (int low , int high){
        ArrayList<String> uniqeIPsInRange = new ArrayList<String>();
        for(LogEntry le : records){
            int statusCode = le.getStatusCode();
            if(statusCode>=low && statusCode<=high){
                String ipAddress = le.getIpAddress();
                if(!uniqeIPsInRange.contains(ipAddress)){
                    uniqeIPsInRange.add(ipAddress);
                }
            }
        }
        return uniqeIPsInRange.size();
     }
     public void printAllHigherThanNum(int num){
        for(LogEntry le : records){
            int statusCode = le.getStatusCode();
            if(statusCode>num){
                System.out.println(le);
            }
        }
    }
    public HashMap<String,Integer> countVisitsPerIP(){
        for(LogEntry le : records){
            String ip = le.getIpAddress();
            if(!counts.containsKey(ip)){
                counts.put(ip,1);
            } else {
                counts.put(ip,counts.get(ip)+1);
            }
        }
        return counts;
    }
    public int mostNumberVisitsByIP(HashMap<String,Integer> myMap){
        int max = 0;
        myMap = countVisitsPerIP();
        for(String line : myMap.keySet()){
            int value = myMap.get(line);
            if(value > max){
                max = value;
            }
        }
        return max;
    }
    public ArrayList<String> iPsMostVisits(HashMap<String, Integer> myMap){
        ArrayList <String> IPs = new ArrayList <String>();
        int max = mostNumberVisitsByIP(myMap);
        for(String line : myMap.keySet()){
            if(myMap.get(line) == max){
                IPs.add(line);
            } 
        }
        return IPs;
    }
    public HashMap<String , ArrayList<String> > iPsForDays(){
        for(LogEntry le : records){
            Date time = le.getAccessTime();
            String day = time.toString();
            day = day.substring(4,10);
            String ip = le.getIpAddress();
            if(!ipsForDays.containsKey(day)){
                ArrayList<String> newIP = new ArrayList <String>();
                newIP.add(ip);
                ipsForDays.put(day,newIP);
            } else {
                ArrayList currIP = ipsForDays.get(day);
                currIP.add(ip);
                ipsForDays.put(day,currIP);
            }
        }
        return ipsForDays;
    }
    public String dayWithMostIPVisits(HashMap<String, ArrayList<String>> mapDays){
        mapDays = iPsForDays();
        int maxValue = 0;
        String mostVisitedDay = null;
        for(String day : mapDays.keySet()){
            int value = mapDays.get(day).size();
            if(value > maxValue){
                maxValue = value;
                mostVisitedDay = day;
            }
        }
        return mostVisitedDay;
    }
    public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, ArrayList<String>> mapDays, String day){
        ArrayList<String> ipsMostVisit = new ArrayList<String>();
        int maxNum = 0;
        mapDays = iPsForDays();
        ArrayList<String> ipsOnDay = mapDays.get(day);
        HashMap<String, Integer> countIps = new HashMap<String, Integer>();
        for(int i = 0; i<ipsOnDay.size(); i++){
            String ip = ipsOnDay.get(i);
            if(!countIps.containsKey(ip)){
                countIps.put(ip,1);
            } else {
                countIps.put(ip, countIps.get(ip) + 1);
            }
        }
        for(String ip : countIps.keySet()){
            if(countIps.get(ip)>maxNum){
                maxNum = countIps.get(ip);
            }
        }
        for(String ip : countIps.keySet()){
            if(countIps.get(ip) == maxNum){
                ipsMostVisit.add(ip);
            }
        }
        return ipsMostVisit;
    }

}
