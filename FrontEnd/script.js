
let tasks = JSON.parse(localStorage.getItem('zgHeroTasks')) || [];
let currentFilter = 'ALL';


function updateLocalStorage() {
    localStorage.setItem('zgHeroTasks', JSON.stringify(tasks));
}

function saveTask() {
    const id = document.getElementById('taskId').value;
    const title = document.getElementById('title').value;
    const description = document.getElementById('description').value;
    const status = document.getElementById('status').value;
    const deadline = document.getElementById('deadline').value;

    if (!title) {
        alert("Por favor, dê um título para a tarefa!");
        return;
    }

    if (id) {
        
        const index = tasks.findIndex(t => t.id == id);
        if (index > -1) {
            tasks[index] = { ...tasks[index], title, description, status, deadline };
        }
    } else {
        
        const newTask = {
            id: Date.now(),
            title,
            description,
            status,
            deadline,
            notified: false
        };
        tasks.push(newTask);
    }

    updateLocalStorage(); 
    clearForm();
    renderTasks();
}

function editTask(id) {
    const task = tasks.find(t => t.id == id);
    if (task) {
        document.getElementById('taskId').value = task.id;
        document.getElementById('title').value = task.title;
        document.getElementById('description').value = task.description;
        document.getElementById('status').value = task.status;
        document.getElementById('deadline').value = task.deadline;

        document.getElementById('btnSave').innerText = "Atualizar Tarefa";
        document.getElementById('btnCancel').style.display = "inline";
    }
}

function deleteTask(id) {
    if (confirm("Tem certeza que deseja excluir esta tarefa?")) {
        tasks = tasks.filter(t => t.id != id);
        updateLocalStorage(); 
        renderTasks();
    }
}

function clearForm() {
    document.getElementById('taskId').value = '';
    document.getElementById('title').value = '';
    document.getElementById('description').value = '';
    document.getElementById('status').value = 'TODO';
    document.getElementById('deadline').value = '';

    document.getElementById('btnSave').innerText = "Salvar Tarefa";
    document.getElementById('btnCancel').style.display = "none";
}

function filterTasks(status) {
    currentFilter = status;
    document.querySelectorAll('.filters button').forEach(btn => btn.classList.remove('active'));
    document.getElementById(`filter-${status}`).classList.add('active');
    renderTasks();
}


function applyBulkAction() {
    
    const newStatus = document.getElementById('bulkStatusSelect').value;

    
    const checkboxes = document.querySelectorAll('.task-checkbox:checked');

    if (checkboxes.length === 0) {
        alert("Selecione pelo menos uma tarefa!");
        return;
    }

    
    checkboxes.forEach(chk => {
        const id = chk.value; 
        const taskIndex = tasks.findIndex(t => t.id == id);
        if (taskIndex > -1) {
            tasks[taskIndex].status = newStatus;
        }
    });

    
    updateLocalStorage();
    renderTasks();
    alert(`${checkboxes.length} tarefas atualizadas para ${newStatus}!`);
}

function renderTasks() {
    const list = document.getElementById('taskList');
    list.innerHTML = '';

    const filteredTasks = currentFilter === 'ALL'
        ? tasks
        : tasks.filter(t => t.status === currentFilter);

    filteredTasks.forEach(task => {
        const item = document.createElement('li');
        item.className = `task-item ${task.status}`;

        const dateStr = task.deadline ? new Date(task.deadline).toLocaleString() : 'Sem prazo';

        
        item.innerHTML = `
            <div style="display:flex; align-items:center; width:100%;">
                <div class="task-checkbox-area">
                    <input type="checkbox" class="task-checkbox" value="${task.id}">
                </div>
                
                <div class="task-info" style="flex-grow:1;">
                    <h3>${task.title}</h3>
                    <p>${task.description}</p>
                    <div class="task-meta">
                        📅 ${dateStr} | Status: <strong>${task.status}</strong>
                    </div>
                </div>
                
                <div class="actions">
                    <button onclick="editTask(${task.id})"><span class="material-icons">edit</span></button>
                    <button onclick="deleteTask(${task.id})" class="btn-delete"><span class="material-icons">delete</span></button>
                </div>
            </div>
        `;
        list.appendChild(item);
    });
}


setInterval(() => {
    const now = new Date();
    let changed = false;
    tasks.forEach(task => {
        if (task.deadline && task.status !== 'DONE') {
            const taskDate = new Date(task.deadline);
            if (now >= taskDate && !task.notified) {
                alert(`⏰ ALARME: A tarefa "${task.title}" venceu!`);
                task.notified = true;
                changed = true;
            }
        }
    });
    if(changed) updateLocalStorage(); 
}, 30000);

renderTasks();