import com.sun.xacml.Indenter;
import com.sun.xacml.PDP;
import com.sun.xacml.PDPConfig;
import com.sun.xacml.ctx.RequestCtx;
import com.sun.xacml.ctx.ResponseCtx;
import com.sun.xacml.ctx.Result;
import com.sun.xacml.finder.AttributeFinder;
import com.sun.xacml.finder.PolicyFinder;
import com.sun.xacml.finder.impl.CurrentEnvModule;
import com.sun.xacml.finder.impl.FilePolicyModule;
import com.sun.xacml.finder.impl.SelectorModule;

import java.io.FileInputStream;
import java.io.PrintStream;
import java.util.*;

public class main {

    public static void main(String args[]) throws Exception {
        System.out.println("Inicio Programa");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Número de política: ");
        int politica = scanner.nextInt();

        String pathPolitica = "C:\\Users\\Public\\policy\\XACMLPolicy" + politica + ".xml";

        PDP pdp = creaPolitica(pathPolitica);

        System.out.println("Número de Petición: ");
        int peticion = scanner.nextInt();

        String pathPeticion = "C:\\Users\\Public\\requests\\XACMLRequest" + peticion + ".xml";

        RequestCtx request = RequestCtx.getInstance(new FileInputStream(pathPeticion));

        ResponseCtx response = pdp.evaluate(request);

        System.out.println("Resultado de la petición " + peticion + " con la política " + politica + " :");

        PrintStream out = new PrintStream(System.out);

        Indenter indenter =  new Indenter();

        Iterator it = response.getResults().iterator();
        indenter.in();
        while (it.hasNext()) {
            Result result = (Result)(it.next());
            result.encode(out, indenter);
        }
        indenter.out();
    }

    private static PDP creaPolitica(String pathPolitica){

        FilePolicyModule filePolicyModule = new FilePolicyModule();
        filePolicyModule.addPolicy(pathPolitica);

        PolicyFinder policyFinder = new PolicyFinder();
        Set policyModules = new HashSet();
        policyModules.add(filePolicyModule);
        policyFinder.setModules(policyModules);

        CurrentEnvModule envAttributeModule = new CurrentEnvModule();
        SelectorModule selectorAttributeModule = new SelectorModule();

        AttributeFinder attributeFinder = new AttributeFinder();
        List attributeModules = new ArrayList();
        attributeModules.add(envAttributeModule);
        attributeModules.add(selectorAttributeModule);
        attributeFinder.setModules(attributeModules);

        return new PDP(new PDPConfig(attributeFinder, policyFinder, null));
    }
}
