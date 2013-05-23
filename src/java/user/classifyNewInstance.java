/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

/**
 *
 * @author drgeek
 */
@WebServlet(name = "classifyNewInstance", urlPatterns = {"/classifyNewInstance"})
public class classifyNewInstance extends HttpServlet {

    public FastVector createInstance() {
//1. parents        usual, pretentious, great_pret
//2. has_nurs       proper, less_proper, improper, critical, very_crit
//3. form           complete, completed, incomplete, foster
//4. children       1, 2, 3, more
//5. housing        convenient, less_conv, critical
//6. finance        convenient, inconv
//7. social         non-prob, slightly_prob, problematic
//8. health         recommended, priority, not_recom
//9. class          not_recom, recommend, very_recom, priority, spec_prior

        FastVector atts = new FastVector();

        FastVector attVals1 = new FastVector();
        attVals1.addElement("usual");
        attVals1.addElement("pretentious");
        attVals1.addElement("great_pret");
        atts.addElement(new Attribute("parents", attVals1));

        FastVector attVals2 = new FastVector();
        attVals2.addElement("proper");
        attVals2.addElement("less_proper");
        attVals2.addElement("improper");
        attVals2.addElement("critical");
        attVals2.addElement("very_crit");
        atts.addElement(new Attribute("has_nurs", attVals2));

        FastVector attVals3 = new FastVector();
        attVals3.addElement("complete");
        attVals3.addElement("completed");
        attVals3.addElement("incomplete");
        attVals3.addElement("foster");
        atts.addElement(new Attribute("form", attVals3));

        FastVector attVals4 = new FastVector();
        attVals4.addElement("1");
        attVals4.addElement("2");
        attVals4.addElement("3");
        attVals4.addElement("more");
        atts.addElement(new Attribute("children", attVals4));

        FastVector attVals5 = new FastVector();
        attVals5.addElement("convenient");
        attVals5.addElement("less_conv");
        attVals5.addElement("critical");
        atts.addElement(new Attribute("housing", attVals5));

        FastVector attVals6 = new FastVector();
        attVals6.addElement("convenient");
        attVals6.addElement("inconv");
        atts.addElement(new Attribute("finance", attVals6));

        FastVector attVals7 = new FastVector();
        attVals7.addElement("non-prob");
        attVals7.addElement("slightly_prob");
        attVals7.addElement("problematic");
        atts.addElement(new Attribute("social", attVals7));

        FastVector attVals8 = new FastVector();
        attVals8.addElement("recommended");
        attVals8.addElement("priority");
        attVals8.addElement("not_recom");
        atts.addElement(new Attribute("health", attVals8));

        FastVector attValsClass = new FastVector();
        attValsClass.addElement("not_recom");
        attValsClass.addElement("recommend");
        attValsClass.addElement("very_recom");
        attValsClass.addElement("priority");
        attValsClass.addElement("spec_prior");
        atts.addElement(new Attribute("class", attValsClass));
        return atts;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
//            Instances data1 = ....
//File out=new File("C:\\...");
//     //save ARFF
//    ArffSaver saver = new ArffSaver();
//    saver.setInstances(data1);
//    saver.setFile(out);
////    saver.setDestination(out);
//    saver.writeBatch();
//      System.out.println("Zapisano");

            String parameters[] = {"parents", "has_nurs", "form", "children", "housing", "finance", "social", "health"};
            String parametersValues[] = new String[parameters.length];
            for (int i = 0; i < parameters.length; i++) {
                parametersValues[i] = request.getParameter(parameters[i]);

            }
            Instances data;
            Classifier cls; //klasyfikator
            //wczytywanie modelu oraz pliku .arff ze spamem
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("C:/Users/drgeek/Desktop/Data Mining/PS_IAI/IAI_PROJECT/nursery_model.model"));
            data = new Instances(new BufferedReader(new FileReader("C:/Users/drgeek/Desktop/Data Mining/PS_IAI/IAI_PROJECT/nursery.data.arff")));
            data.setClassIndex(data.numAttributes() - 1);
            cls = (Classifier) ois.readObject();
            cls.buildClassifier(data);
            //System.out.println(cls.);

            Instances dataset = new Instances("nursery", createInstance(), 0);

            //wczytywanie parametrow z servletu
            double[] vals = new double[dataset.numAttributes()];
            System.out.println("" + dataset.numAttributes());
            for (int i = 0; i < dataset.numAttributes() - 1; i++) {
                vals[i] = Double.valueOf(parametersValues[i]);
            }
            //utworzenie nowej instancji
            Instance inst = new Instance(1.0, vals);
            dataset.add(inst);
            dataset.setClassIndex(data.numAttributes() - 1);
            double clsLabel = cls.classifyInstance(dataset.instance(0));
            dataset.instance(0).setClassValue(clsLabel);

            String instanceClass = dataset.attribute(data.classIndex()).value((int) clsLabel);
            instanceClass = instanceClass.equals("not_recom") ? "Nie wskazane"
                    : (instanceClass.equals("recommend") ? "Wskazane"
                    : (instanceClass.equals("very_recom") ? "Bardzo wskazane"
                    : (instanceClass.equals("priority") ? "Konieczne"
                    : (instanceClass.equals("spec_prior") ? "Absolutnie niezbędne" : "lol"))));
//            switch(instanceClass){
//                //not_recom, recommend, very_recom, priority, spec_prior
//                case "not_recom": instanceClass = "Nie wskazane"; break;
//            }
            //String instanceClass = (dataset.attribute(0).value((int) clsLabel).equals("Yes")) ? "Tak" : "Nie";
            Evaluation eval = new Evaluation(data);
            eval.evaluateModel(cls, data);
            String evalInfo = eval.toSummaryString("Statyki z ewaluacji", false);
            request.setAttribute("class", instanceClass);
            request.setAttribute("evaluation", evalInfo);
            request.getRequestDispatcher("classificationResult.jsp").forward(request, response);

        } finally {
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(classifyNewInstance.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(classifyNewInstance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(classifyNewInstance.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(classifyNewInstance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
