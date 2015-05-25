package adaptor;

import static org.junit.Assert.assertNotNull;
import models.adaptor.AdaptorChoco;
import models.bean.Model;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class AdaptorChocoTest {

	private Model modelMock;

	@Before
	public void setUp() throws Exception{
		this.modelMock = EasyMock.createMock(Model.class);
		
		assertNotNull("précondition", this.modelMock);
	}
	
	@Test
	public void testGetNbPersons() 
	{
		System.out.println(this.modelMock.getPersons());
		EasyMock.expect(this.modelMock.getPersons().size()).andReturn(5);
		
		EasyMock.replay(this.modelMock);
		
		AdaptorChoco ac = new AdaptorChoco(this.modelMock);
		ac.getNbPersons();
		
		EasyMock.verify(this.modelMock);
	}
}
