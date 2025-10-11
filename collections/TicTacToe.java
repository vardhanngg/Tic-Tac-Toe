package collections;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
public class TicTacToe{
    int box[][]={
        {0,0,0},
        {0,0,0},
        {0,0,0},    
    };
    Node lbi=new Node(0,0);//last bot index
    Node lhi=new Node(0,0);//last human
    public int rowsum(Node node){
        int r=node.x;
        //int c=node.y;
        int sum=0;
        for(int i=0;i<3;i=i+1){
          sum=sum+box[r][i];
        }
        return sum;
    }
    public int colsum(Node node){
        //int r=node.x;
        int c=node.y;
        int sum=0;
        for(int i=0;i<3;i=i+1){
          sum=sum+box[i][c];
        }
        return sum;
    }
    public int diag1sum(Node node){
        int r=node.x;
        int c=node.y;
        if(c==r)
          return (box[0][0]+box[1][1]+box[2][2]);
        return -1;
    }
    public int diag2sum(Node node){
        int r=node.x;
        int c=node.y;
        if(c+r==2)
          return (box[2][0]+box[1][1]+box[0][2]);
        return -1;
    }
    public Node iswinning(){
          //system.out.println("in Trying iswinning");
          if(rowsum(lbi)==10){
            //system.out.println("in rowsum of win");
            int r=lbi.x;
            int sum=0;
            for(int i=0;i<3;i=i+1){
                if(box[r][i]==0)
                    return new Node(r,i);
        }
          }
          //system.out.println("in col sum of "+lbi.x+" "+lbi.y+" is "+colsum(lbi));
          if(colsum(lbi)==10){
          //system.out.println("in colsum win");
            int c=lbi.y;
            for(int i=0;i<3;i=i+1){
              if(box[i][c]==0)
                  return new Node(i,c);
        }
          }
          if(diag2sum(lbi)==10){
              //system.out.println("in diag2sum win");
              if(box[2][0]==0)
                  return new Node(2,0);
              if(box[1][1]==0)
                  return new Node(1,1);
              if(box[0][2]==0)
                  return new Node(0,2);
          }
          if(diag1sum(lbi)==10){
         //system.out.println("in diag1sum win");
              if(box[0][0]==0)
                  return new Node(0,0);
              if(box[1][1]==0)
                  return new Node(1,1);
              if(box[2][2]==0)
                  return new Node(2,2);
          }
          
         return new Node(-1,-1);
    }
    
        public Node isloosing(){
          if(rowsum(lhi)==2){
          //system.out.println("in row loose");
            int r=lhi.x;
            int sum=0;
            for(int i=0;i<3;i=i+1){
                if(box[r][i]==0)
                    return new Node(r,i);
        }
          }
          //system.out.println("in col sum of "+lhi.x+" "+lhi.y+" is "+colsum(lhi));
          if(colsum(lhi)==2){
          //system.out.println("in col loose ");
            int c=lhi.y;
            for(int i=0;i<3;i=i+1){
              if(box[i][c]==0)
                  return new Node(i,c);
        }
          }
          if(diag2sum(lhi)==2){
          //system.out.println("in diag2 loose");
              if(box[2][0]==0)
                  return new Node(2,0);
              if(box[1][1]==0)
                  return new Node(1,1);
              if(box[0][2]==0)
                  return new Node(0,2);
          }
          if(diag1sum(lhi)==2){
         //system.out.println("in diag1 loose");
              if(box[0][0]==0)
                  return new Node(0,0);
              if(box[1][1]==0)
                  return new Node(1,1);
              if(box[2][2]==0)
                  return new Node(2,2);
          }
          
         return new Node(-1,-1);
    }
    
   /* public Node findblank(){
        
        if(box[1][1]==0){
            //system.out.println("in found blank space 1,1" );
            return new Node(1,1);
        }
        for(int r=0;r<3;r=r+1){
          for(int c=0;c<3;c=c+1){
              if(box[r][c]==0){
               //system.out.println("in found blank space "+r+" "+c );
                return new Node(r,c);
              }
          }
        }
        return new Node(-1,-1);
    }*/
    public Node findblank(){//using random
        Random rand = new Random();
        List<Node> Index = new ArrayList<>();
        int x = rand.nextInt(3);
        int y =rand.nextInt(3);
        for(int r=0;r<3;r=r+1){
          for(int c=0;c<3;c=c+1){
              if(box[r][c]==0){
                Index.add( new Node(r,c));
              }
            }
        }
        if (Index.isEmpty())
          return new Node(-1,-1);
        return Index.get(rand.nextInt(Index.size()));

          
      }
    public  void print(){
        for (int[] row : box) {
            for (int c : row){
              if(c==0)
                System.out.print(c+" ");
              if(c==5)
                System.out.print("b ");
              if(c==1)
                System.out.print("x ");
            }
            System.out.println();
        }
    }
    
    public void play(){
        System.out.println("Welcome! You are x, I am b");
        System.out.print("Choose 1 to start first or choose any other number: ");
        Node t=null;
        Scanner s = new Scanner(System.in);
        int c=s.nextInt();
        if(c!=1){
            t=findblank();
            box[t.x][t.y]=5;
            lbi.x=t.x;
            lbi.y=t.y;           
        }
        print();
        
        int[] human = new int[]{0, 0};
        s = new Scanner(System.in);
        String[] hArr = new String[]{""};
        String hStr;
        //int a=-1, b=-1;
        
        while(true){
            t=findblank();
            if(t.x==-1){
                System.out.println("Draw 🤝");
                return;
              }
          System.out.print("Choose eg:(0,0): ");
          hStr = s.nextLine();
          hArr = hStr.split(",");
          human = new int[]{Integer.parseInt(hArr[0]), Integer.parseInt(hArr[1])};
          //System.out.println(human[0]+" "+human[1]);
          while(human[0]<0 || human[1]<0 || human[0]>2 || human[1]>2){
            System.out.println("Invalid choice!");
            System.out.print("Choose: ");
            hStr = s.nextLine();
            hArr = hStr.split(",");
            human = new int[]{Integer.parseInt(hArr[0]), Integer.parseInt(hArr[1])};
          
          }
          while(box[human[0]][human[1]]!=0){
            System.out.println("Already filled!");
            System.out.print("Choose: ");
            hStr = s.nextLine();
            hArr = hStr.split(",");
            human = new int[]{Integer.parseInt(hArr[0]), Integer.parseInt(hArr[1])};
          }
          box[human[0]][human[1]]=1;
          lhi.x=human[0];
          lhi.y=human[1];          
          if(colsum(lhi)==3 || rowsum(lhi)==3 || diag1sum(lhi)==3 || diag2sum(lhi)==3 ){
              print();
              System.out.println("You win! 👍");
              return;
          }
          if(iswinning().x != -1){
              t=iswinning(); 
              box[t.x][t.y]=5;
              lbi.x=t.x;
              lbi.y=t.y;
              print();
              System.out.println("I win you FOOL! 🤡");
              return;
          }
          if(isloosing().x!=-1){
              t=isloosing();
              box[t.x][t.y]=5;
              lbi.x=t.x;
              lbi.y=t.y;
          }
          else{
            t=findblank();
            if(t.x==-1){
                System.out.println("Draw 🤝");
                return;
            }
            box[t.x][t.y]=5;
            lbi.x=t.x;
            lbi.y=t.y;
          }
          print();
        }
    }

}
