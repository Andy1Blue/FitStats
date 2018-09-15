package pl.krzysztofbujak.fitstat.services;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import pl.krzysztofbujak.fitstat.entities.DataEntity;
import pl.krzysztofbujak.fitstat.entities.LogsEntity;
import pl.krzysztofbujak.fitstat.entities.UsersEntity;
import pl.krzysztofbujak.fitstat.forms.LoginForm;
import pl.krzysztofbujak.fitstat.repositories.LogsRepository;
import pl.krzysztofbujak.fitstat.repositories.UsersRepository;

@Service
@Scope(scopeName = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
@Setter
public class LoginService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    LogsRepository logsRepository;

    private boolean isLogin;
    private boolean isUserExist;
    private boolean isLoginFail;
    private String author;
    private int loginId;

    public LoginService() {
        isLogin = false;
        isLoginFail = false;
    }

    public void login(LoginForm loginForm) {
        UsersEntity usersEntity = usersRepository.findByLogin(loginForm.getLogin());

        if (usersEntity != null && usersEntity.getPassword().equals(loginForm.getPassword())) {
            isLogin = true;
            author = usersEntity.getLogin();
            UsersEntity usersEntityForId = usersRepository.findIdByLogin(author);
            loginId = usersEntityForId.getId();

            LogsEntity logsEntity = new LogsEntity();
            logsEntity.setUsers(usersEntityForId);
            logsEntity.setIp("0.0.0.0.0");
            logsRepository.save(logsEntity);
        } else {
            isLogin = false;
        }
    }

    public boolean isExist(LoginForm loginForm) {
        Iterable<UsersEntity> users = usersRepository.findAll();

        for (UsersEntity user : users) {
            if (user.getLogin().equals(loginForm.getLogin())) {
                isUserExist = true;
                return false;
            }

        }
        return true;
    }

    public void register(LoginForm loginForm) {
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setLogin(loginForm.getLogin());
        usersEntity.setPassword(loginForm.getPassword());

        usersRepository.save(usersEntity);
    }

}
