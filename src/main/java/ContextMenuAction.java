import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import icons.gui.EditorGroupingDialog;
import org.jetbrains.annotations.NotNull;

public class ContextMenuAction extends AnAction {

  @Override
  public void actionPerformed(@NotNull AnActionEvent event) {
    Project currentProject = event.getProject();
    new EditorGroupingDialog(currentProject).show();
  }

  @Override
  public void update(@NotNull AnActionEvent e) {
    Project project = e.getProject();
    e.getPresentation().setEnabledAndVisible(project != null);
  }

}
