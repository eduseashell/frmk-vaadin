package edu.kwon.frmk.vaadin.gui.profile.list;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.vaadin.data.Item;

import edu.kwon.frmk.common.data.jpa.repository.entities.audit.AuditEntityService;
import edu.kwon.frmk.common.data.jpa.repository.profile.Profile;
import edu.kwon.frmk.common.data.jpa.repository.profile.ProfileService;
import edu.kwon.frmk.common.share.spring.util.I18N;
import edu.kwon.frmk.vaadin.component.table.Column;
import edu.kwon.frmk.vaadin.gui.layout.crud.AbstractMainLayout;
import edu.kwon.frmk.vaadin.gui.layout.crud.AbstractSearchPanel;
import edu.kwon.frmk.vaadin.gui.layout.search.panel.SimpleSearchPanel;
import edu.kwon.frmk.vaadin.util.helper.StringHelper;

/**
 * @author eduseashell
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class ProfileMainLayout extends AbstractMainLayout<Profile> {

	private static final long serialVersionUID = 1375067285884334517L;
	
	@Autowired
	private ProfileService profileService;
	@Autowired
	private SimpleSearchPanel<Profile> searchPanel;
	
	@Override
	protected void init() {
		super.init();
		setCaption(I18N.string("profiles"));
	}

	@Override
	protected List<Column> buildTableColumn() {
		List<Column> columns = new ArrayList<Column>();
		columns.add(new Column(Profile.ID, I18N.string("id"), Long.class, 70));
		columns.add(new Column(Profile.CODE, I18N.string("code"), String.class, 200));
		columns.add(new Column(Profile.DESC, I18N.string("desc"), String.class, 300));
		columns.add(new Column(Profile.ACTIVE, I18N.string("status"), String.class, 70));
		return columns;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void renderRow(Item item, Profile profile) {
		item.getItemProperty(Profile.ID).setValue(profile.getId());
		item.getItemProperty(Profile.CODE).setValue(profile.getCode());
		item.getItemProperty(Profile.DESC).setValue(profile.getDesc());
		item.getItemProperty(Profile.ACTIVE).setValue(StringHelper.toActiveMsg(profile.getActive()));
	}
	
	@Override
	protected AbstractSearchPanel<Profile> onCreateSearchPanel() {
		return searchPanel;
	}

	@Override
	protected AuditEntityService<Profile> getService() {
		return profileService;
	}

}
